module.exports = {
   create,
   info,
   participar,
   historico,
   resolver,
   userInfo
};

var q = require('q');

function create(req, res) {

   q.spawn(function *() {
      try {
         console.log(req.user)
         var porra = yield Porra.create({
            owner: req.user,
            amount: req.body.amount || 1.0,
            descripcion: req.body.descripcion || 'sin descripcion',
            status: 'playing',
            type: req.body.type,
         });

         yield porra.save();
         porra = yield PorraService.participar(req.user, porra, req.body.value);
         res.send(porra)
      } catch (err) {
         res.serverError(err)
      }
   })


}

function info(req, res) {
   q.spawn(function *() {
      var porra = yield Porra.findOne({where: {id: req.param('porraId')}}).populate('participaciones');
      if (!porra) {
         res.notFound();
      }
      res.send(porra)
   })
}

function participar(req, res) {

   q.spawn(function *() {
      try {
         var porra = yield Porra.findOne({where: {id: req.param('porraId')}});
         porra = yield PorraService.participar(req.user, porra, req.body.value);
         res.send(porra)
      } catch (err) {
         res.serverError(err)
      }
   })
}

function historico(req, res) {
   q.spawn(function *() {

      var user = yield User.findOne({
         or: [
            {id: req.user.id},
            {username: req.user.id}
         ]
      }).populate('participaciones');

      function getPorra(porraId) {
         return Porra.findOne({id: porraId});
      }

      var promises = [];
      _.forEach(user.participaciones, (participacion) => {
         promises.push(getPorra(participacion.porra).then(function (porra) {
            participacion.porra = porra;
            delete participacion.porra.participaciones;
         }));
      });

      yield q.all(promises);

      res.send(user)
   })
}

function resolver(req, res) {
   q.spawn(function *() {

      try {
         var porra = yield PorraService.resolver(req.param('porraId'), req.param('winnerId'));
         res.send(porra)
      } catch (err) {
         res.serverError(err)
      }
   })
}

function userInfo(req, res) {
   res.send(req.user)
}

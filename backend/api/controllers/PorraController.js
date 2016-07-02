module.exports = {
   create,
   info,
   participar,
   historico
};

var q = require('q')

function create(req, res) {

   q.spawn(function *() {
      try {
         var porra = yield Porra.create({
            owner: req.user,
            amount: req.body.amount || 99,
            descripcion: req.body.descripcion || 'sin descripcion'
         })

         yield porra.save();
         var porra = yield PorraService.participar(req.user, porra);
         res.send(porra)
      } catch (err) {
         res.serverError(err)
      }
   })


}

function info(req, res) {
   q.spawn(function *() {
      var porra = yield Porra.findOne({where: {id: req.param('porraId')}}).populate('participantes');
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
         porra = yield PorraService.participar(req.user, porra);
         res.send(porra)
      } catch (err) {
         res.serverError(err)
      }
   })
}

function historico(req, res) {
   q.spawn(function *() {

      var user = yield User.findOne({id: req.user.id}).populate('jugando');

      res.send(user)
   })
}

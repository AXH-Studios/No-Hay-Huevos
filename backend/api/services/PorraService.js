module.exports = {
   participar,
   resolver
};

var q = require('q');

function participar(user, porra, value) {

   user.cartera -= porra.amount;
   porra.participaciones.add({
      user: user.id,
      porra: porra.id,
      value: value
   });

   return porra.save().then(function(){
      return user.save();
   }).then(function () {
      return Porra.findOne({where: {id: porra.id}}).populate('participaciones');
   });

}

function resolver(porraId, winnerId) {
   console.log("AAAAAAAAAAAAa", porraId, winnerId);

   return Porra.findOne({id: porraId}).populate('participaciones').then(function (porra) {
      var totalGana = (porra.participaciones.length * porra.amount) - 0.10;
      return User.findOne({or: [
         { id: winnerId },
         { username: winnerId }
      ]}).then(function (ganador) {
         porra.winner = ganador;
         porra.status = 'finished';
         ganador.cartera += totalGana;
         return ganador.save();
      }).then(function () {
         return porra.save();
      })
   }).then(function () {
      return Porra.findOne({id: porraId}).populate('participaciones');
   })
}

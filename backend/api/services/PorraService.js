module.exports = {
   participar,
}

var q = require('q')

function participar(user, porra) {

   user.cartera -= porra.amount;
   porra.participantes.add(user.id);

   return porra.save().then(function(){
      return user.save();
   }).then(function () {
      return Porra.findOne({where: {id: porra.id}}).populate('participantes');
   });

}

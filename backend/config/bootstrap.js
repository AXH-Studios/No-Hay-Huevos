/**
 * Bootstrap
 * (sails.config.bootstrap)
 *
 * An asynchronous bootstrap function that runs before your Sails app gets lifted.
 * This gives you an opportunity to set up your data model, run jobs, or perform some special logic.
 *
 * For more information on bootstrapping your app, check out:
 * http://sailsjs.org/#!/documentation/reference/sails.config/sails.config.bootstrap.html
 */

var q = require('q')

module.exports.bootstrap = function (cb) {

   q.spawn(function *() {
      try {
         //Create mock user
         var user1 = yield User.create({
            username: 'pepe@mail.com',
            cartera: 10.0
         })

         var user2 = yield User.create({
            username: 'juan@mail.com',
            cartera: 10.0
         })

         var porra = yield Porra.create({
            owner: user1,
            amount: 2.0,
            status: 'playing',
            type: 'libre',
            descripcion: "Porra por defecto"
         })

         porra.participaciones.add([{
            user: user1.id,
            porra: porra.id,
            value: '1-1'
         }, {
            user: user2.id,
            porra: porra.id,
            value: '2-1'
         }]);
         yield porra.save();
         porra = yield Porra.findOne({ where: { id: porra.id }}).populate('participaciones')

         console.log(user1)
         console.log(user2)
         console.log(porra)

      } catch (e) {
         console.error(e)
      } finally {
         // It's very important to trigger this callback method when you are finished
         // with the bootstrap!  (otherwise your server will never lift, since it's waiting on the bootstrap)
         cb();
      }

   })
};

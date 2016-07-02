/**
 * sessionAuth
 *
 * @module      :: Policy
 * @description :: Simple policy to allow any authenticated user
 *                 Assumes that your login action in one of your controllers sets `req.session.authenticated = true;`
 * @docs        :: http://sailsjs.org/#!/documentation/concepts/Policies
 *
 */

var q = require('q')

module.exports = function (req, res, next) {

   q.spawn(function*() {
      try {
         console.log("USER:", req.param('userId'))
         req.user = yield User.findOne({ where: { username: req.param('userId') }});

         // if(!req.user) {
         //    return res.forbidden('You are not permitted to perform this action.');
         // }

         return next();
      } catch (e) {
         console.error(e)
         res.serverError()
      }
   })

};

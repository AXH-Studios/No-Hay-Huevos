module.exports = {
   attributes : {
      user : {
         model : 'User'
      },
      porra: {
         model: 'Porra'
      },
      value: {
         type : 'string',
         required : true
      }
   },
   popularUsuario: function() {
      return this.populate('user');
   }
}

module.exports = {
   attributes : {
      username : {
         type : 'string',
         required : true
      },
      cartera : {
         type : 'float',
         required : true
      },
      jugando: {
         collection: 'Porra',
         via: 'participantes'
      }
   }
}

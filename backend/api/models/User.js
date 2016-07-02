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
      participaciones: {
         collection: 'Participacion',
         via: 'user'
      }
   }
}

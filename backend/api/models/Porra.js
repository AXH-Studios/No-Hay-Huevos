module.exports = {
   attributes : {
      owner : {
         model: 'User'
      },
      amount: {
         type : 'float',
         required : true
      },
      descripcion: {
         type : 'string',
         required : true
      },
      participantes: {
         collection: 'User',
         via: 'jugando',
         dominant: true
      }
   }
};

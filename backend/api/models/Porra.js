module.exports = {
   attributes : {
      owner : {
         model: 'User'
      },
      amount: {
         type : 'float',
         required : true
      },
      status: {
         type : 'string',
         required : true
      },
      type: {
         type : 'string',
         required : true
      },
      descripcion: {
         type : 'string',
         required : true
      },
      participaciones: {
         collection: 'Participacion',
         via: 'porra'
      },
      winner : {
         model: 'User'
      }
   }
};

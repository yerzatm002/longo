require('dotenv').config();

const mongoose = require('mongoose');

const connectionStr = 'mongodb+srv://mukhammed:mukhammed@cluster0.gxhya.mongodb.net/longoWeb?retryWrites=true&w=majority';


mongoose.connect(connectionStr, {useNewUrlparser: true})
.then(() => console.log('connected to mongodb'))
.catch(err => console.log(err))

mongoose.connection.on('error', err => {
  console.log(err)
})

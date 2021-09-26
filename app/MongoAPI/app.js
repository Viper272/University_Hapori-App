const express = require('express');
const app = express();
const mongoose = require('mongoose');
require('dotenv/config')

app.use(express.json());

const servicesRouter = require('./routes/services');
app.use('/services', servicesRouter);

mongoose.connect(
    process.env.DB_CONNECTION,
    {useNewUrlParser: true},
    () => console.log("Connected to DB")
);

app.listen(12346);





//WEBSITES:
/*
https://github.com/AndroidHitchhiking/ArticlesWithMongoSample/tree/master/Android/app/src/main/java/khan/shadik/mongoarticle
https://www.youtube.com/watch?v=vjf774RKrLc&ab_channel=DevEd
https://medium.com/@shadikkhan/mongodb-step-by-step-part-5-rest-api-with-node-and-consumed-in-android-app-8ccc1ffaef91

Consuming APIS
https://guides.codepath.com/android/consuming-apis-with-retrofit
https://medium.com/android-news/consuming-rest-api-using-retrofit-library-in-android-ed47aef01ecb
*/
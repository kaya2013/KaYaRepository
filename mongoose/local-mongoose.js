//import mongoose
var mongoose = required("mongoose");

//connect test db
var connection = mongoose.connect( "mongodb://localhost:27017/test, {useMongoClient:true}" );

//action, if
mongoose.connection.once("open", function(){
    console.log("Monggose connection successfull....");
});

mongoose.connection.once("close", function(){
    console.log("Mongoose connection close successfull....");
});

mongoose.disconnect();
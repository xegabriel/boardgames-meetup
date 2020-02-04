const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const MongoClient = require('mongodb').MongoClient;
const mongoDbConString = 'mongodb://vivi:1234@boardgamescluster-shard-00-00-2fdfc.gcp.mongodb.net:27017,boardgamescluster-shard-00-01-2fdfc.gcp.mongodb.net:27017,boardgamescluster-shard-00-02-2fdfc.gcp.mongodb.net:27017/test?ssl=true&replicaSet=BoardgamesCluster-shard-0&authSource=admin&retryWrites=true&w=majority';
let db;

MongoClient.connect(process.env.MONGODB_URI || mongoDbConString, (err, database) => {
  if (err) return console.log(err)
  db = database.db('test')
  app.listen(process.env.PORT || 8080, () => {
    console.log('listening on 8080')
  })
})

app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
app.use(express.static('public'))

app.get('/topUsers', (req, res) => {
  let noToReturn = 3;
  if (req.query && req.query['no']) {
    noToReturn = parseInt(req.query['no']);
  }
  console.log(`Returning top: ${noToReturn} users.`)
  db.collection('users').find({}, { projection: { '_id': 0, 'email': 1, 'firstName': 1, 'lastName': 1, 'numberOfAttendedGames': 1 } }).limit(noToReturn).sort({ 'numberOfAttendedGames': -1 }).toArray((err, users) => {
    if (err) {
      console.error(`There was an error while trying to retrieve users. ${err}`);
      res.status(500).send("ERROR")
    } else {
      console.log(`Found ${users.length} users.`)
      res.status(200).send(users);
    }
  });
});
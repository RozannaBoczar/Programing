const Pool = require('pg').Pool

//const bodyParser = require('body-parser')
var fs = require('fs')
var publicDir = require('path').join(__dirname,'/stronka');

//database user but how to share a database XD?
const pool = new Pool({
  user: 'me',
  host: 'localhost',
  database: 'DB_PROJ_BOCZAR_WRÓBLEWSKA',
  password: 'anka',
  port: 5432,
})

const getRezerwacje = (request, response) => {
  //showing everything from table users after going to localhost:3000/users
  pool.query('SELECT * FROM rezerwacje', (error, results) => {
    if (error) {
      throw error
    }
    //response.status(200).json(results.rows)
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/rezerwacje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
    });
    fs.readFile('stronka/rezerwacje.html', function(err, data) {
      response.writeHead(200, {'Content-Type': 'text/html'});
      //response.send(wp)
      response.write(data);
      response.end();

    })
  })
}


const getPokoje = (request, response) => {

  pool.query('SELECT * FROM pokoje order by p', (error, results) => {
    if (error) {
      throw error
    }
    //response.status(200).json(results.rows)
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/pokoje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
    });
    fs.readFile('stronka/index5.html', function(err, data) {
      response.writeHead(200, {'Content-Type': 'text/html'});
      //response.send(wp)
      response.write(data);
      response.end();

    })
  })
}

const getWolnepokoje = (request, response) => {
  pool.query('SELECT * FROM pokoje where dostepnosc=true', (error, results) => {
    if (error) {
      throw error
    }
    var wp = JSON.stringify(results.rows);
    //choose_free_rooms(wp)
    //console.log(wp);
    fs.writeFile("stronka/wolnepokoje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
    });

    fs.readFile('stronka/index3.html', function(err, data) {
      response.writeHead(200, {'Content-Type': 'text/html'});
      //response.send(wp)
      response.write(data);
      response.end();

    })

    //response.status(200).json(results.rows)
      //getPokoje_site(wp);


  })

}

const getStandardy = (request, response) => {

  pool.query('SELECT * FROM standardy', (error, results) => {
    if (error) {
      throw error
    }
    //response.status(200).json(results.rows)
    var wp = JSON.stringify(results.rows);
    //choose_free_rooms(wp)
    //console.log(wp);
    fs.writeFile("stronka/standardy.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
    });

    fs.readFile('stronka/standardy.html', function(err, data) {
      response.writeHead(200, {'Content-Type': 'text/html'});
      //response.send(wp)
      response.write(data);
      response.end();

    })
  })
}



const createRezerwacja = (request, response) => {
  const { imie, nazwisko, p } = request.body

  pool.query('select dostepnosc from pokoje where p = $1', [p],(error, results)=>{
    var wp = JSON.stringify(results.rows);
    var e = JSON.parse(wp);
    console.log(e[0]['dostepnosc']);
    if(error){
      throw error;
      console.log('łeee');
    }
    if(e[0]['dostepnosc']==false){
      console.log("eh");
      fs.readFile('stronka/index10.html', function(err, data) {
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.write(data);
        response.end();
      })
      //response.send('ktoś już zajal!');
    }
    else{
      pool.query('INSERT INTO rezerwacje (imie, nazwisko, p) VALUES ($1, $2,$3)', [imie, nazwisko, p], (error, results) => {
        if (error) {
          throw error
        }
        updatetables()
        //response.status(201).send(`<h4>Dodano rezerwację</h4>`)

      })

      pool.query('UPDATE pokoje SET dostepnosc = false WHERE p = $1', [p], (error, results) => {
        if (error) {
          throw error
          console.log('aaaaaaaaaaa')
        } else {
          updatetables()
          console.log('bbbbbbbb')
          console.log(results)
        }
        updatetables()


          //response.json({ info: 'Node.js, Express, and Postgres API' })

        //response.status(200).send('Zmieniono status pokoju nr $1', [p])

      })

      fs.readFile('stronka/index7.html', function(err, data) {
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.write(data);
        response.end();
      })
    }
  })



}

const updateRezerwacja = (request, response) => {
  const { r, p } = request.body
  updatetables();
  console.log(r)
  row = r.split(',');
  console.log(row[0]);

  pool.query('SELECT p FROM rezerwacje WHERE r = $1', [row[0]], (error, results) => {
    console.log(results)
    if (results.rowCount == 0 ){
      console.log("TO TO");
      updatetables()
      fs.readFile('stronka/index10.html', function(err, data) {
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.write(data);
        response.end();
      })
    }else if (results.rows[0].p!=row[1]) {
      console.log("TO TO");
      updatetables()
      fs.readFile('stronka/index10.html', function(err, data) {
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.write(data);
        response.end();
      })
    }
    else{
    //console.log(results)
    //console.log("TO TO");
   var pokoj= results.rows[0].p;


    //console.log(results.rows[0].p);
    if (error) {
        //throw error
        //if(results.rows[0].p)
    }
    else{
      pool.query('select dostepnosc from pokoje where p = $1', [p],(error, results)=>{
        var wp = JSON.stringify(results.rows);
        var e = JSON.parse(wp);
        console.log(e[0]['dostepnosc']);
        if(error){
          throw error;
          console.log('łeee');
        }
        if(e[0]['dostepnosc']==false || r[1] == pokoj ){
          console.log("eh");
          fs.readFile('stronka/index10.html', function(err, data) {
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.write(data);
            response.end();
          })
          //response.send('ktoś już zajal!');
        }
        else{
          pool.query('UPDATE pokoje SET dostepnosc = true WHERE p = $1 ', [pokoj] , (error, results) => {
            if (error) {
              throw error
              console.log('cccc')
            } else {
              console.log(results)
              console.log('zmieniono pokoj nr ' + pokoj +' na wolny')
              updatetables()
            }
          })
          pool.query('UPDATE rezerwacje SET p = $1 WHERE r = $2', [p, row[0]], (error, results) => {
            if (error) {
              throw error
            }
            pool.query('UPDATE pokoje SET dostepnosc = false WHERE p = $1 ', [p] , (error, results) => {
              if (error) {
                throw error
                console.log('cccc')
              } else {
                console.log(results)
                console.log('zmieniono pokoj nr ' + p +' na zajety')
                updatetables()
              }
            })
            console.log("edytowano rezerwacje");
            updatetables()
            fs.readFile('stronka/index9.html', function(err, data) {
              response.writeHead(200, {'Content-Type': 'text/html'});
              response.write(data);
              response.end();
            })
            //response.status(201).send(`<h4>Zaktualizowano rezerwację</h4>`)
          })
        }
        })

}
}})
}

const deleteRezerwacja = (request, response) => {
  //console.log(request.param("r"));
  var numer_pokoju;
  var p;
  const r = request.param("r");
  //  pool.query('select r from rezerwacje where r = $1', [r],(error, results)=>{

  pool.query('SELECT p FROM rezerwacje WHERE r = $1', [r], (error, results) => {
    if (error) {
      throw error
    }
    else if(results.rowCount==0){
      updatetables()
      fs.readFile('stronka/index10.html', function(err, data) {
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.write(data);
        response.end();
      })

    }
    else {
      //console.log('pokoj numer: ')
      //console.log(results)
      console.log('pokoj:')
      numer_pokoju = parseInt(results.rows[0].p)
      p = results.rows[0].p
      console.log(numer_pokoju)
      console.log(p)
      pool.query('DELETE FROM rezerwacje WHERE r = $1', [r], (error, results) => {
        if (error) {
          throw error;
        }
        updatetables()
        //response.status(200).send(`Usunięto rezerwację: ${r}`)


        pool.query('UPDATE pokoje SET dostepnosc = true WHERE p = $1 ', [p] , (error, results) => {
          if (error) {
            throw error
            console.log('cccc')
          } else {
            console.log(results)
            console.log('zmieniono pokoj nr ' + numer_pokoju +' na wolny')
            updatetables()
          }
        })
      })
      fs.readFile('stronka/index8.html', function(err, data) {
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.write(data);
        response.end();
      })
    }

  })

}

//updateuje nam pliki z tabelkami :')
const updatetables = (request, response) => {

  pool.query('SELECT * FROM standardy', (error, results) => {
    if (error) {
      throw error
    }
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/standardy.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
  })
});

  pool.query('SELECT * FROM pokoje', (error, results) => {
    if (error) {
      throw error
    }
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/pokoje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
  })
  });

  pool.query('SELECT * FROM rezerwacje', (error, results) => {
    if (error) {
      throw error
    }
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/rezerwacje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
  })
  });

  pool.query('SELECT * FROM pokoje where dostepnosc = true', (error, results) => {
    if (error) {
      throw error
    }
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/wolnepokoje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
  })
  });


  pool.query('SELECT * FROM pokoje where dostepnosc = false', (error, results) => {
    if (error) {
      throw error
    }
    var wp = JSON.stringify(results.rows);
    fs.writeFile("stronka/zajetepokoje.json", wp, function(err) {
    if (err) {
      //  console.log(err);
    }
  })
});
}



module.exports = {
  getRezerwacje,
  getPokoje,
  getWolnepokoje,
  getStandardy,
  createRezerwacja,
  deleteRezerwacja,
  updateRezerwacja,
  updatetables,
}

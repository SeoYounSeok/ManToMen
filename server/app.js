var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var tokencheck = require('./tokenhelp');

mongoose.connect('mongodb://localhost:27017/login',{useNewUrlParser:true})

var indexRouter = require('./routes/index');
var classRouter = require('./routes/class');
var homeRouter = require('./routes/home');
var app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public/index')));
app.use(express.static(path.join(__dirname, 'public/images')));

app.use('/home', homeRouter);
app.use('/class', tokencheck, classRouter);
app.use('/index', tokencheck ,indexRouter);
//app.use('/class', classRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


http.listen(80, function () {
  console.log('app listening on port 80!');
});

module.exports = app;

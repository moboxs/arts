const fs = require('fs');

fs.readFile('./test', (err, doc) => {
    if (err) {
        //
    } else {
        console.log(doc.toString());
    }
});



function log(x, y) {
    y = y || 'word';
    console.log(x, y);
}

function log2(x, y='world') {
    console.log(x, y);
}

log2('Hello');

// function foo(x = 5) {
//     let x = 1;
//     const x = 3;
// }

/*
let x = 99;
function foo(y = x+1) {
    console.log(y);
}
foo();
x = 200;
foo();
foo(20);
*/

/*
function foo({x, y=1}) {
    console.log(x, y);
}
foo({x:"hello", y:1112});
*/

/*
function fetch(url, {body = '', method = 'GET', header = {}}) {
    console.log(method);
}
fetch('http://baidu.com', {});
*/

/*
function throwIfMissing() {
    throw new Error('Missing parameter');
}
function foo(mustBeProvided = throwIfMissing()) {
    return mustBeProvided;
}
let res = foo(333);
console.log(res);
*/

/*
function add(...values) {
    let sum = 0;
    for (var val of values) {
        sum += val;
    }
    return sum;
}
let sum = add(2,3,5);
console.log(sum);
*/
/*
function sortNumbers() {
    return Array.prototype.slice.call(arguments).sort();
}

const sortNumbers = (...Numbers) => Numbers.sort();
let res = sortNumbers('12', '112', '312');
console.log(res);
*/
/*
function push(array, ...items) {
    items.forEach(function (item) {
        array.push(item);
        console.log(item);
    });
}
let a = [];
push(a, 1,2,3);
console.log(a);
*/

function factorial(n) {
    if(n===1) return 1;
    return n * factorial(n-1);
}
let total = factorial(3);
console.log(total);
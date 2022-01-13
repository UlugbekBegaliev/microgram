'use strict'

function showSplashScreen(){
    document.getElementById('page-splash').hidden = false;
	document.body.classList.add('no-scroll');
}

function hideSplashScreen(){
    document.getElementById('page-splash').hidden = true;
	document.body.classList.remove('no-scroll');
}

window.addEventListener('load', function () {

    const savePostButton = document.getElementById("btnSubmit");
    if(savePostButton != undefined) {

        savePostButton.addEventListener("click", function () {
            const newPost = document.getElementById("newPost");
            let data = new FormData(newPost);
            console.log(data);

            fetch("http://localhost:9889", {
                method: 'POST',
                body: data
            }).then(r => r.json()).then(data => {
                window.location.href = "http://localhost:9889"
            });
        });
    }

    let justContent = document.getElementById("postContent");
        if(justContent != undefined) {
            fetch("http://localhost:9889",)
                // .then(response => response.text())
                .then(text => console.log(text))
                .then(data => data)
        }
});

async function f() {
    const formData = new FormData();
    const fileField = document.querySelector('input[type="file"]');

    formData.append('username', 'user777');
    formData.append('avatar', fileField.files[0]);

    try {
        const response = await fetch('http://localhost:9889', {
            method: 'POST',
            body: formData
        });
        const result = await response.json();
        console.log('Готово!:', JSON.stringify(result));
        console.log(formData);
    } catch (error) {
        console.error('Ошибка!', error);
    }


}
f();
class User {
  constructor(id, name, email, isAuthorised) {
    this.id = id,
    this.name = name,
    this.email = email,
    this.isAuthorised = isAuthorised;
  }
};

class Comment {
  constructor(user, post, comment) {
    this.user = user,
    this.post = post,
    this.comment = comment;
  }
};
class Post {
  constructor(id, userId, image, description) {
    this.id = id,
    this.userId = userId,
    this.image =  image,
    this.description = description;
  }
};

function authorize(user) {
  user.isAuthorised = true;
}
const user = new User(1, "Robby Williams", "rwilliams@gmail.com", false);
authorize(user);
const post = new Post(1, user.id, "images/img59.jpg",  "Lorem ipsum dolor sit. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque libero minima corporis accusamus nobis velit.");
const user2 = new User(2, "Robin Martin","rmartin@gmail.com", false);
authorize(user2);
const comment = new Comment(user2.name, post.id, "I am very grateful for the meeting with you and I would like to discuss other commercial issues.");

let posts = [];

function newPost(i) {
  return new Post(i+2, user.id,  "text" + i, 0);
};

for(let i = 0; 6 > i; i++) {
  posts[i] = newPost(i);
};

function toPrint(post) {

};
posts.forEach(toPrint);

function like (post, postId, isLiked) {
  if(post.id === postId) {
    isLiked ? post.likes++ : post.likes--;
  };
};

let postId = 4;

let isLiked = true;

for(let i = 0; i < posts.length; i++) {
  like(posts[i], postId, isLiked);
};

  const signIn= document.getElementById('button');
  signIn.addEventListener('click', hideSplashScreen);

function createCommentElement(comment){
    let elem= document.createElement('div')
    elem.classList.add('py-2');
    elem.classList.add('pl-3');
    elem.innerHTML = '<a href="#" class="muted">'  + comment.user + '</a>' +
  '<p>' + comment.comment + '</p>';
  return elem;
}

function addPost(postElement){
    document.getElementById("posts-container").append(postElement);
}

let postsCont = createPostElement(post);
addPost(postsCont);
document.getElementById('comments').append(createCommentElement(comment));

const hearts = document.getElementsByClassName('fa-heart');
for(let i = 0; i < hearts.length; i++) {
    hearts[i].addEventListener('click', function() {
        if(hearts[i].classList.contains('fas')) {
            hearts[i].classList.remove('fas');
            hearts[i].classList.remove('text-danger');
            hearts[i].classList.add('far');
        } else {
            hearts[i].classList.remove('far');
            hearts[i].classList.add('text-danger');
            hearts[i].classList.add('fas');
        }
    })
};

const bookmark = document.getElementsByClassName('fa-bookmark');
for(let i=0; i< bookmark.length; i++){
  bookmark[i].addEventListener('click',function(){
    if(bookmark[i].classList.contains('fas')){
        bookmark[i].classList.remove('fas');
        bookmark[i].classList.add('far');
    } else {
        bookmark[i].classList.remove('far');
        bookmark[i].classList.add('fas');
    }
  })
}

const posts = document.getElementsByClassName('card');
for(let i = 0; i < posts.length; i++) {
    let ims = posts[i].getElementsByClassName('w-100');
    let he = posts[i].getElementsByClassName('fa-heart')[0];
    for(let j = 0; j < ims.length; j++) {
        ims[j].addEventListener('dblclick', function() {
          if(he.classList.contains('fas')) {
            he.classList.remove('fas');
            he.classList.remove('text-danger');
            he.classList.add('far');
           } else {
            he.classList.remove('far');
            he.classList.add('text-danger');
            he.classList.add('fas');
           }
        })
    }
};

 var fromm = document.getElementById('btnButton')
fromm.addEventListener('click', function (e) {
    e.preventDefault();
    let test = document.getElementById( 'test').value;

    fetch("https://jsonplaceholder.typicode.com/comments" , {
        method: 'POST',
        body:JSON.stringify({
            title:test
        }),
        headers: {
            "Content-Type":"application/json; charset=UTF-8"
        }
    }).then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data)
        let div = document.getElementById('newCom');
        let elem= document.createElement('div')
        elem.classList.add('py-2');
        elem.classList.add('pl-3');
        elem.innerHTML = `<a href="#" class="muted">${data.id}</a><p>${data.title}</p>`;
        div.append(elem);
    })

})

const url = 'https://jsonplaceholder.typicode.com/todos';

async  function fetchAsyncTodos() {
    console.log('fetch started')
    try{
        const response = await fetch(url);
        const data = await response.json();
        console.log('Data: ', data);
    } catch (e) {
        console.error(e)
    }
}
fetchAsyncTodos();















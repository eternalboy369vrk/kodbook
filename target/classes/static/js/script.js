/**
 * 
 */

/*
const heartBtn = document.getElementById('heart-btn');
  console.log('Heart button element:', heartBtn);

  let initialLikedState = localStorage.getItem('liked');
  console.log('Initial liked state:', initialLikedState);

  let liked = initialLikedState === 'true';
  console.log('Liked state:', liked);

  if (liked) {
    heartBtn.classList.add('liked');
    console.log('Added liked class');
  }

  heartBtn.addEventListener('click', function() {
    console.log('Button clicked!');
    liked = !liked;
    localStorage.setItem('liked', liked ? 'true' : 'false');
    heartBtn.classList.toggle('liked');
    console.log('Liked state changed to:', liked);
  });
*/

const heartBtns = document.querySelectorAll('.heart-button');

    heartBtns.forEach((heartBtn) => {
        const postId = heartBtn.id.split('-')[1];
        const initialLikedState = localStorage.getItem(`liked-${postId}`);
        let liked = initialLikedState === 'true';

        if (liked) {
            heartBtn.classList.add('liked');
        }

        heartBtn.addEventListener('click', function() {
            liked = !liked;
            localStorage.setItem(`liked-${postId}`, liked ? 'true' : 'false');
            heartBtn.classList.toggle('liked');
        });
    });

/*
 const heartBtn = document.getElementById('heart-btn');
   heartBtn.addEventListener('click', function() {
     heartBtn.classList.toggle('clicked');
  });  */
/**
 * 
 */

// Get all post images
const postImages = document.querySelectorAll('.card');

// Add event listener to each post image
postImages.forEach((image) => {
  image.addEventListener('click', (event) => {
    // Get the enlarged image URL
    const imgUrl = event.target.src;

    // Get the modal and modal image elements
    const modal = document.querySelector('.modal');
    const modalImg = document.querySelector('.modal-img');

    // Set the modal image src and display the modal
    modalImg.src = imgUrl;
    modal.style.display = 'block';
  });
});

// Add event listener to the close button
const closeButton = document.querySelector('.close');
closeButton.addEventListener('click', () => {
  const modal = document.querySelector('.modal');
  modal.style.display = 'none';
});


const imageUpload = document.querySelector('.image-upload input');
const videoUpload = document.querySelector('.video-upload input');

imageUpload.addEventListener('change', (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.onload = (e) => {
        const imageData = e.target.result;
        // Do something with the image data
    };
    reader.readAsDataURL(file);
});

videoUpload.addEventListener('change', (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.onload = (e) => {
        const videoData = e.target.result;
        // Do something with the video data
    };
    reader.readAsDataURL(file);
});


/*

    const postTypeRadios = document.getElementsByName('postType');

    postTypeRadios.forEach((radio) => {
        radio.addEventListener('change', (event) => {
            const postType = event.target.value;
            const imageUploadDiv = document.getElementById('image-upload');
            const videoUploadDiv = document.getElementById('video-upload');

            if (postType === 'IMAGE') {
                imageUploadDiv.style.display = 'block';
                videoUploadDiv.style.display = 'none';
            } else if (postType === 'VIDEO') {
                imageUploadDiv.style.display = 'none';
                videoUploadDiv.style.display = 'block';
            } else {
                imageUploadDiv.style.display = 'none';
                videoUploadDiv.style.display = 'none';
            }
        });
    });




    $(document).ready(function() {
        $('#image-button').click(function() {
            $('#image-input').show();
            $('#video-input').hide();
            $('#text-input').show();
        });
        $('#video-button').click(function() {
            $('#image-input').hide();
            $('#video-input').show();
            $('#text-input').show();
        });
        $('#text-button').click(function() {
            $('#image-input').hide();
            $('#video-input').hide();
            $('#text-input').show();
        });
    });
	*/

<?php
$image = $_POST['image'];
$decodedImage = base64_decode($image);
// image name & path //php tay 2 ta string dot . dia jukto koraty hoy .. 
//rand =random ..that mean 1-1000 ar modde akta number nibay karon user akoi somoy onek jon astay paray ...
$fileName = time() . '_' . rand(1, 1000) . '.jpg';//time = mili second convert hoilo .. //under score dici নিজের সুবিদায় জাতে নামের পর number দিলে buja jay
$imagePath = "images/" . $fileName; //php tay images name a ekta folder create kori...
if (isset($image)) {
if (file_put_contents($imagePath, $decodedImage)) {
echo "image সঠিক ভাবে upload হয়েছে।";
} else {
echo "image upload হয়নি।";
}
} else {
echo "কোনো image নেই।";
}

?>

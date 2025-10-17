<?php

$image64 = $_POST['image'];
$decodedImage = base64_decode($image64);

if ( file_put_contents('my_file.jpeg', $decodedImage) ) {// my_file.jpeg এই নামে database এ লিঙ্ক hit korbay  
    echo 'Image Upload Successful'; //apps a এই লেখা টা show korbay ..
} else {
    echo 'Image Upload Failed... :(';
}

?>

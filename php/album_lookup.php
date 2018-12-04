<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
$album = $_POST["album"];
// $album = "Wish You Were Here";



$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}

$sql="select album_name,release_year,artist_name,genre_name from album,artists,genre where album_name='$album' and artists.artist_id=album.artist_id and genre.genre_id=album.genre_id";


$result = $link->query($sql);
$value=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $value[]=$row;
    }
    echo(json_encode($value));
} else {
    echo "404";
}
mysqli_close($link);

?>


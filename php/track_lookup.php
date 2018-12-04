
<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
$track = $_POST["track"];
// $track ="You Never Give Me Your Money";


$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="SELECT album_name,artist_name, release_year FROM track, album, artists where track.album_id=album.album_id and track.artist_id=artists.artist_id and track_name='$track'";

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


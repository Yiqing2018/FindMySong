
 # Find Your Song

### demo video
https://www.youtube.com/watch?v=ix-FVjCw3jM&feature=youtu.be

### Development Environment
Android Studio 3.2.1
### Server
cPanel, Mysql  

****  
### Project description
 Our project is going to be centered around Spotify. We are going to use Spotify API’s to access user data and listening history. We will use this information to construct databases to keep track of musical tastes. These databases will then be used to evaluate similarity between multiple user’s music tastes and to make recommendations to users on music they would all like.  
 ****

### workflow  
![](https://ws4.sinaimg.cn/large/006tNbRwly1fxuj3u549aj319j0u043x.jpg)


****
### Functionality

#### Basic functions: 
##### login in 
Login in with Spotify API, we will import your user information(user_id, username) and recent listening history into our backend database.  if you successfully login in, redirect to home page of our application. 


<p align="center">

  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxucqkav5xj30u01hcdip.jpg">
  
  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxucro1d0lj30u01hcdpg.jpg">

</p>

##### Recent Trend 
show Recent Trend (Top popularity tracks,albums,artists)  

<p align="center">

  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxs0wm22zsj30u01hck10.jpg">
  
  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs0zfr30gj30u01hc136.jpg">


  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs0ztgc05j30u01hcgw5.jpg">


  <img width="200" src="https://ws2.sinaimg.cn/large/006tNbRwly1fxs0zwskskj30u01hcteq.jpg">

</p>

click on the Track/Album/Artist for more details

<p align="center">

  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs123a6t3j30u01hcq9s.jpg">
  
  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs127uhtij30u01hc7a8.jpg">

  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs12b6jonj30u01hcgq3.jpg">

</p>

##### Search

Search by tracks, artists, albums  

<p align="center">
	  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs130b6glj30u01hcabc.jpg">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1357ew4j30u01hcgng.jpg">
	  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxs13c72lbj30u01hc76z.jpg">
	  <img width="200" src="">
</p>

##### follow/unfollow
Follow/unfollow other users  
<p align="center">
	  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxs1us939sj30u01hcgn8.jpg">
	  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs1ux6vtoj30u01hcmzq.jpg">
	  <img width="200" src="https://ws2.sinaimg.cn/large/006tNbRwly1fxs1v1a6uvj30u01hc409.jpg">
</p>

<p align="center">	  
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1v4nu67j30u01hcmzs.jpg">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1v7lcz1j30u01hcabp.jpg">
</p>


##### Contact with us    
click on the email icon, you can automatically go to the Email Application.
<p align="center">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1fc7u8dj30u01hcjtm.jpg">
	  <img width="200" src="https://ws2.sinaimg.cn/large/006tNbRwly1fxs1g32welj30u01hcdh4.jpg">
</p>

##### Report     
Leave your message with us!  
Show message if email address is invalid.
<p align="center">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1hrx0e0j30u01hcdh1.jpg">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1hvrobej30u01hc3zl.jpg">
	  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxs1i0g2f3j30u01hcgml.jpg">
</p>
<p align="center">
	  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs1gypqazj30u01hc0tx.jpg">
	  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxs1h3mcrvj30u01hc3zm.jpg">
</p>
<p align="center">
	  <img width="600" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs1rfpkxsj30w40803zo.jpg">
</p>

#### Advanced functions:
 
##### Score with your friends!  
compare how similar with your friends. click on another user you are following, it will show a progress bar which indicates how similar you are with your friends.  
we use this algorithm to calculate the similarity:  
- same tracks, Plus 10 points!  
- same album, Plus 8 points!  
- same artist, Plust 6 points!  

<p align="center">
	  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs1a4e62tj30u01hcq42.jpg">
	  <img width="200" src="https://ws3.sinaimg.cn/large/006tNbRwly1fxs1aiggzwj30u01hcabw.jpg">
	  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs1aofjsej30u01hctak.jpg">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1av1oyfj30u01hc76e.jpg">
</p>  

##### Find your Song!  
we followed the algorithm:  
- find the most similar another user (based on track listening history) 
- get the listening list of that user, find out which songs I haven't listened befored.  
- show a track of that list randomly  

<p align="center">
	  <img width="200" src="https://ws1.sinaimg.cn/large/006tNbRwly1fxs1e4ycisj30u01hcaaw.jpg">
	  <img width="200" src="https://ws4.sinaimg.cn/large/006tNbRwly1fxs1e9ditnj30u01hcq3o.jpg">
	  <img width="200" src="https://ws2.sinaimg.cn/large/006tNbRwly1fxs1el0f5pj30u01hcgmr.jpg">
</p>  


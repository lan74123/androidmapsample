package work.samplemap;

import work.samplemap.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*先放著Location import
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
*/

public class MainActivity extends FragmentActivity {
	final LatLng TaiwanCenterPoint = new LatLng(23.9740930, 120.9799030); //坐標抓法 可參考上篇
	private GoogleMap mMap; //建立mMap繼承 GoogleMap
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    }
    @Override
	protected void onResume() {
		super.onResume();
		initMap(); //產生map 自訂method
	}
    private void initMap(){
    	if(mMap==null){
    		mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap(); 
    		//沒有就抓產生的map，這行有限定minSdkVersion為11以上，如果出現錯誤訊息試著改 manifest.xml的android:minSdkVersion為"11"以上
    	}
    	if(mMap!=null){
    		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); //map type 有多種
    	}
    	mMap.setMyLocationEnabled(true); //顯示現在位置
    	mMap.getUiSettings().setMyLocationButtonEnabled(true); //定位按鈕 右上角
    	mMap.getUiSettings().setMapToolbarEnabled(true); //設定工具欄
    	//Marker 1 設立標記
    	MarkerOptions markerOpt = new MarkerOptions(); //物件導向 繼承MarkerOptions的method
    	markerOpt.position(TaiwanCenterPoint); //前面有標記的點
    	markerOpt.title("地理中心碑"); //設定名稱
    	markerOpt.snippet("地理中心就在這裡！"); //註解文字
    	markerOpt.draggable(false); //無法拖移
    	markerOpt.visible(true); //是否顯現
    	markerOpt.anchor(0.5f, 0.5f); //標記在地點位置的下方？上方 設定 基本上不用修改這欄
    	//markerOpt.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation)); //可自訂圖示 不自訂就會使用內建
    	mMap.addMarker(markerOpt); //加入marker
    }
    	/*
    	Position(必要)：使用 LatLng 類別來設定位置，這是唯一必要設定的屬性。
    	Title：使用者點一下標記時顯示的標題文字。
    	Snippet：額外的文字，顯示在標題文字下方。
    	Draggable：是否可以讓使用者移動標記，true 可移動；false 則否。
    	Visible：是否顯示標記，true 顯示；false隱藏。
    	Anchor：圖片上的一個點，用來定位到經緯度座標，預設為圖片的中間下緣。值為左上角(0.0, 0.0)到右下角(1.0, 1.0)。
    	Icon：圖示，被放置在原標記的相同位置，只有第一次建立標記時可以使用圖示，之後就不能任意更換圖示。
    	*/
}

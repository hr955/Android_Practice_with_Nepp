## 📁 Colosseum_20210903 ( 토론 앱 )
#### API 실습 ( 서버통신 )
- Library : OkHttp
- 주소?type=... (Query) : `.addEncodedQueryParameter` 
- 주소/카테고리/id/... (Path) : `.addPathSegment`
- getString/getJSONObject / getJSONArray...

#### 푸시알림
- brary : FCM (Firebase Colud Messaging)

#### 코틀린 응용
- companion object -> static 처럼 동작
- SharedPreferences
- runOnUiThread
  - Background Thread 에서 MainThread에 UI 조작을 요청
  - UI 조작은 MainThread(UI Thread)에서만 가능하기 때문
- constructor(보조 생성자)
- Button.tag
- Validation (입력값 검증)
- CustomActionBar (BaseActivity에서 적용)
- 어댑터에서 액티비티 함수 사용하기 (다형성)
  - Type Casting -> (context as activity).function
- 여러개의 버튼이 같은 동작을 할 때 : `val 변수 = object: View.onClickListener{ override fun onClick{ } }`
- 모든 액티비티를 종료 (로그아웃에서 활용) : `Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK`
<br/>

## 📁 JickbangCopy_20210820
- ListView 실습
- Serializable (커스텀 데이터 클래스를 인텐트에 담아 전달하기)
- Handler ( SplashActivity -> postDelay )
<br/>

## 📁 Pizza_Order_App (안드로이드 기초 응용)
- Fragment + TabLayout + ViewPager 
- ListView 활용
- 평문 통신( http ) 허용 : `usesCleartextTraffic = "true"` 
- Serializable ( 커스텀 데이터 클래스를 인텐트에 담아 전달하기 )
- onActivityResult ( 닉네임 변경시 액티비티 왕복 )

- Library 
  - Glide ( 인터넷 권한 필요 )
  - CircleImageView
  - Lottie

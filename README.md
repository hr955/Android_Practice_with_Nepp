## ๐ Colosseum_20210903 ( ํ ๋ก  ์ฑ )
#### API ์ค์ต ( ์๋ฒํต์  )
- Library : OkHttp
- ์ฃผ์?type=... (Query) : `.addEncodedQueryParameter` 
- ์ฃผ์/์นดํ๊ณ ๋ฆฌ/id/... (Path) : `.addPathSegment`
- getString/getJSONObject / getJSONArray...

#### ํธ์์๋ฆผ
- brary : FCM (Firebase Colud Messaging)

#### ์ฝํ๋ฆฐ ์์ฉ
- companion object -> static ์ฒ๋ผ ๋์
- SharedPreferences
- runOnUiThread
  - Background Thread ์์ MainThread์ UI ์กฐ์์ ์์ฒญ
  - UI ์กฐ์์ MainThread(UI Thread)์์๋ง ๊ฐ๋ฅํ๊ธฐ ๋๋ฌธ
- constructor(๋ณด์กฐ ์์ฑ์)
- Button.tag
- Validation (์๋ ฅ๊ฐ ๊ฒ์ฆ)
- CustomActionBar (BaseActivity์์ ์ ์ฉ)
- ์ด๋ํฐ์์ ์กํฐ๋นํฐ ํจ์ ์ฌ์ฉํ๊ธฐ (๋คํ์ฑ)
  - Type Casting -> (context as activity).function
- ์ฌ๋ฌ๊ฐ์ ๋ฒํผ์ด ๊ฐ์ ๋์์ ํ  ๋ : `val ๋ณ์ = object: View.onClickListener{ override fun onClick{ } }`
- ๋ชจ๋  ์กํฐ๋นํฐ๋ฅผ ์ข๋ฃ (๋ก๊ทธ์์์์ ํ์ฉ) : `Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK`
<br/>

## ๐ JickbangCopy_20210820
- ListView ์ค์ต
- Serializable (์ปค์คํ ๋ฐ์ดํฐ ํด๋์ค๋ฅผ ์ธํํธ์ ๋ด์ ์ ๋ฌํ๊ธฐ)
- Handler ( SplashActivity -> postDelay )
<br/>

## ๐ Pizza_Order_App (์๋๋ก์ด๋ ๊ธฐ์ด ์์ฉ)
- Fragment + TabLayout + ViewPager 
- ListView ํ์ฉ
- ํ๋ฌธ ํต์ ( http ) ํ์ฉ : `usesCleartextTraffic = "true"` 
- Serializable ( ์ปค์คํ ๋ฐ์ดํฐ ํด๋์ค๋ฅผ ์ธํํธ์ ๋ด์ ์ ๋ฌํ๊ธฐ )
- onActivityResult ( ๋๋ค์ ๋ณ๊ฒฝ์ ์กํฐ๋นํฐ ์๋ณต )

- Library 
  - Glide ( ์ธํฐ๋ท ๊ถํ ํ์ )
  - CircleImageView
  - Lottie

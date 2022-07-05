# MeChuRi

## Tab 1: 연락처 앱

* 기존 로컬 연락처 연동
  * 이름
  * 전화번호
  * 사진
  * 세부 정보 창
  * 이메일 주소
  * 주소
![KakaoTalk_Photo_2022-07-05-20-23-27 002](https://user-images.githubusercontent.com/99390261/177316904-1718ae9d-c0f3-43bf-b1b5-7dcdc4c8ccf3.png)
  
* Item setOnclick Listener (Recycler view 안의 item을 클릭할 시)
  * 세부 정보, 이메일 주소, 주소 등 자세한 정보 표기

* Intent를 사용한 외부 어플리케이션 이동 (위 사진 휴대전화, 메세지, 메일 버튼 참고)
  * 전화 기능
  * 메세지 기능
  * 메일 기능
  
* Add feature
  * Add로 새로운 연락처 추가 가능
  * 사진 업로드
  * 이름 및 번호 입력
  * 자동 동기화
![KakaoTalk_Photo_2022-07-05-20-23-27 003](https://user-images.githubusercontent.com/99390261/177317026-c9ba9a12-7b5b-416b-b108-c04017e34ccb.png)

* Delete feature
  * Delete 버튼을 누르고 item 을 클릭해서 삭제할 아이템 선택 가능
  * 확인 버튼으로 최종 confirm 가능 (하단 사진 option bar 체크 버튼)
![KakaoTalk_Photo_2022-07-05-20-23-27 001](https://user-images.githubusercontent.com/99390261/177317101-01cbc0c2-d056-4857-ac24-9774fc46531d.png)

* option bar 이용으로 불 필요한 버튼 제거

* SharedPreference 를 사용한 내부 영구 저장소 이용 (추가하거나 삭제한 정보가 앱 종류 후 유실되지 않음)

## Tab 2: 갤러리 앱

* 갤러리 자동 연동

![KakaoTalk_Photo_2022-07-05-20-27-13 004](https://user-images.githubusercontent.com/99390261/177318066-97725c7f-1dd8-4ba9-b1c6-e3d137055349.png)

* pager를 사용한 사진 item 터치 후 자연스럽게 다른 이미지로 이동 가능
  * 자연스러운 pager 이동 모션 구현

![KakaoTalk_Photo_2022-07-05-20-27-13 001](https://user-images.githubusercontent.com/99390261/177318186-1724d959-88ca-4eea-9be6-75b3583663ae.png)

* Shared preference 를 사용한 영구 저장소 이용

![KakaoTalk_Photo_2022-07-05-20-27-13 002](https://user-images.githubusercontent.com/99390261/177318090-de1c2f2b-5e4c-445f-9ba1-96f16366fb2f.png)

## Tab 3: 메뉴 추천 앱 ft.110여개의 카이스트 주변 맛집 정보

### (1) 메뉴 자동 룰렛 추천

![KakaoTalk_Photo_2022-07-05-20-34-22](https://user-images.githubusercontent.com/99390261/177319166-0d2811eb-9152-4441-a816-84d42885b58d.gif)


* 추천 시 dynamic 하게 ImageView와 TextView를 수정

![KakaoTalk_Photo_2022-07-05-20-32-56 002](https://user-images.githubusercontent.com/99390261/177318346-58ae974a-8928-48b6-88df-31318a308e9c.png)

### (2) 맛집 상세 정보
* (1)에서 선택 된 메뉴와 일치하는 맛집 정렬
* pager를 이용한 맛집 정렬 후 자연스러운 이동 가능

![KakaoTalk_Photo_2022-07-05-20-32-56 001](https://user-images.githubusercontent.com/99390261/177318376-0e7c0fa7-0112-4f6d-95d1-fd000088e039.png)

### (3) 맛집 위치 상세 정보
* (2)에서 선택한 맛집 위치와 내 현재 위치 좌표 마킹
* 거리와 이동 시간 계산 후 표기

![KakaoTalk_Photo_2022-07-05-20-33-52](https://user-images.githubusercontent.com/99390261/177318473-32f0ce13-9696-43c6-8cde-0ff8af60e6fd.png)


### (4) 맛집 사용자 추가
* 기존 맛집 이외에 사용자 선택적 추가 기능
* 이름으로 좌표 찾는 API 구현
  * 위치 정보를 선택적 입력 가능 (이름으로 위치 정보 자동 입력)
  
![KakaoTalk_Photo_2022-07-05-20-32-56 003](https://user-images.githubusercontent.com/99390261/177318418-377ab5ca-3d1d-40d1-9c5a-c9464fca3628.png)

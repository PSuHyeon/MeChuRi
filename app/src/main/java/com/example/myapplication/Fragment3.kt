package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import java.util.*


val lunch_menuList: ArrayList<String> = ArrayList<String>()//arrayListOf("함박","덥밥","불고기", "오징어 두루치기", "닭볶음", "쌈밥", "비빔밥", "생선구이", "낙지볶음", "게장", "떡갈비", "김치찌개", "순두부찌개", "된장찌개", "부대찌개", "동태찌개", "청국장", "갈비탕", "추어탕", "삼계탕", "짜장면", "짬뽕", "탕수육", "칸풍기", "유린기", "마파두부", "짬뽕", "볶음밥", "양장피", "고추잡채", "초밥", "오니기리", "야키니쿠", "라멘", "덮밥", "메밀소바", "낫또", "우동", "돈카츠", "토마토 스파게티", "피자", "스테이크", "봉골레", "함박 스테이크", "햄버거", "크림 파스타", "리조또", "시저 샐러드", "북엇국", "뼈해장국", "올갱이국", "콩나물 국밥", "우거지국", "라면", "순대국", "선지해장국", "냉면", "도시락", "샌드위치", "토스트", "샐러드", "김밥", "떡볶이", "핫도그", "샌드위치", "김밥", "밥버거", "토스트", "떡볶이", "시리얼", "쌀국수", "팟타이", "카레", "찜닭", "수제비", "칼국수", "아구찜", "닭갈비", "월남쌈")
val dinner_menuList: ArrayList<String> = lunch_menuList
val place_list: ArrayList<Place> = arrayListOf(
    Place("카레", "맑음", "042-861-0244", 36.36333, 127.35915, "월-토 11:30 - 20:30, 일요일 17:00 - 20:00", R.drawable.akfrdma, null, "대전 유성구 어은로42번길 27"),
    Place("덮밥", "요시다", "0507-1432-7338",36.36361, 127.35861, "매일 11:30 - 20:10", R.drawable.dytlek, null,"대전광역시 유성구 어은로42번길 21-12"),
    Place("함박", "1117 meal", "042-863-7111", 36.36388,127.35795, "매일 11:30 - 20:00", R.drawable.dlfdlfdlfclfmeal, null, "대전 유성구 어은로58번길 28"),
    Place("팟타이", "잇마이타이", "042-335-5466", 36.36361,127.35886, "월-토 11:00 - 21:00, 일요일 휴무", R.drawable.dltakdlxkdl, null, "대전 유성구 어은로58번길 62"),
    Place("샌드위치", "우디룸", "0507-1312-4688", 36.36323, 127.35806, "매일 11:00 - 22:00", R.drawable.dnelfna, null, "대전광역시 유성구 어은동 어은로48번길 19"),
    Place("파스타", "하바쿡", "042-825-3496", 36.36241, 127.35813, "월-토 11:00 - 22:00, 일요일 11:00 - 21:00", R.drawable.gkqkznr, null, "대전 유성구 대학로 227 어은빌딩"),
    Place("초밥", "어은스시", "042-863-5306", 36.36318, 127.35716, "월-토 11:30 - 21:30, 일요일 휴무", R.drawable.djdmstmtl, null, "대전 유성구 어은로48번길 9-11"),
    Place("초밥", "아소부", "042-825-2995", 36.36286, 127.35788, "매일 11:00 - 20:30", R.drawable.dkthqn, null, "대전 유성구 어은로48번길 14"),
    Place("백반", "만개", "0507-1371-2995", 36.36286, 127.35788, "월-금 11:00 - 20:00, 토,일요일 휴무", R.drawable.aksro, null, "대전 유성구 어은로48번길 14"),
    Place("피자", "보보츠", "042-861-9959", 36.36333, 127.35736, "월-토 11:30 - 20:30, 일요일 휴무", R.drawable.qhqhcm, null, "대전 유성구 어은로52번길 1"),
    Place("고기", "화로에굽다", "042-862-3628", 36.36309, 127.35832, "매일 11:00 - 22:00", R.drawable.ghkfhdprnqek, null, "대전 유성구 어은로48번길 24"),
    Place("쌈밥", "형제돌구이", "042-862-6723", 36.36326, 127.35848, "엽업시간 정보 없음", R.drawable.gudwpehfrndl, null, "대전 유성구 어은로48번길 28"),
    Place("백반", "밀과보리", "042-867-3916", 36.36308, 127.35788, "매일 11:30 - 20:00", R.drawable.alfrhkqhfl, null, "대전 유성구 어은로48번길 15"),
    Place("파스타", "무어", "0507-1496-3533", 36.36373, 127.35763, "월-토 11:30 - 21:00", R.drawable.andj, null, "대전 유성구 어은로58번길 20"),
    Place("파스타", "아키바의 식탁 두번째", "0507-1376-9855", 36.36250, 127.35762, "월-금 11:00 - 21:00, 토 11:00 - 15:00, 일요일 휴무", R.drawable.dkzlqkdmltlrxkrenqjswo, null, "대전 유성구 어은로48번길 10-6"),
    Place("돈가스", "하레", "070-8126-4139", 36.36283, 127.35778, "월-토 11:30 - 20:00", R.drawable.gkfp, null, "대전 유성구 어은로48번길 12"),
    Place("돈가스", "다다카츠", "0507-1459-2298", 36.36353, 127.35766, "월-토 11:00 - 21:00, 일요일 휴무", R.drawable.ekekzkcm, null, "대전 유성구 어은로52번길 7"),
    Place("돈가스", "쿠마키친", "0507-1344-8988", 36.36282, 127.35826, "월-금,일 11:00 - 21:00, 토요일 휴무", R.drawable.znakzlcls, null, "대전 유성구 어은로42번길 7"),
    Place("찜닭", "세 번째 우물", "0507-1427-5200", 36.36266, 127.35792, "월-금 11:30 - 21:00 , 토요일 휴무, 일요일은 저녁만", R.drawable.tpqjswodnanf, null, "대전 유성구 어은로48번길 10-5"),
    Place("백반", "어랑족", "042-862-8889", 36.36351, 127.35721, "엽업시간 정보 없음", R.drawable.djfkdwhr, null, "대전 유성구 어은로58번길 12"),
    Place("파스타", "비스트로퍼블릭", "010-8249-0393", 36.36249, 127.35824, "월-금 11:30 - 22:00, 토 12:00 - 22:00, 일요일 휴무", R.drawable.qltmxmfhvjqmfflr, null, "대전 유성구 대학로 229"),
    Place("감자탕", "순우리뼈감자탕", "042-863-0775", 36.36344, 127.35694, "영업시간 정보 없음", R.drawable.tnsdnflqurkawkxkd, null, "대전 유성구 어은로58번길 10"),
    Place("국밥", "맛고을", "042-861-0762", 36.36349, 127.35849, "월-금,일 10:00 - 20:30, 토요일 휴무", R.drawable.aktrhdmf, null, "대전 유성구 어은로48번길 29"),
    Place("고기", "골목", "042-861-9371", 36.36298, 127.35623, "매일 16:30 - 23:00, 1,3,5번째 일요일 휴무", R.drawable.rhfahr, null, "대전 유성구 어은로57번길 5"),
    Place("쌈밥", "쌈의 대가", "0507-1414-3611", 36.36178, 127.35493, "매일 11:30 - 21:00", R.drawable.tkadmleork, null, "대전 유성구 농대로8번길 19"),
    Place("컵밥", "치마치마벤또", "042-867-8693", 36.36145, 127.35513, "화-금 11:00 - 20:00, 토,일 12:00 - 20:00, 월요일 휴무", R.drawable.clakclakqpseh, null, "대전 유성구 농대로2번길 25"),
    Place("피자", "누오보나폴리", "042-322-9582", 36.361952, 127.35299, "수-일 11:30 - 22:00, 월,화요일 휴무", R.drawable.sndhqhskvhffl, null, "대전 유성구 농대로 15"),
    Place("막창", "달구지막창", "042-867-1230", 36.36203, 127.35341, "화-일 17:00 - 새벽 02:00, 월요일 휴무", R.drawable.ekfrnwlakrckd, null, "대전 유성구 어은로57번길 59"),
    Place("초밥", "란스시", "042-861-4561", 36.36229, 127.35509, "월-토 11:30 - 21:00, 일요일 휴무", R.drawable.fkstmtl, null, "대전 유성구 어은로51번길 32"),
    Place("초밥", "겐스시", "0507-1321-8623", 36.36204, 127.35421, "월-금 12:00 - 21:00, 토 17:30 - 21:00, 일요일 휴무" , R.drawable.rpstmtl, null, "대전 유성구 어은로51번길 50"),
    Place("술찜", "작교", "0507-1479-4260", 36.36144, 127.35377, "월-금 17:00 - 새벽 02:00, 토,일 16:00 - 새벽 02:00", R.drawable.wkrry, null, "대전 유성구 농대로2번길 7-11"),
    Place("함박", "136함박", "042-863-0136", 36.36183, 127.35421, "월-금 10:30 - 20:30, 토,일요일 휴무", R.drawable.dlftkadbrgkaqkr, null, "대전 유성구 어은로51번길 51"),
    Place("초밥", "Ato", "0507-1316-7145",36.36196, 127.35194, "화-토 11:30 - 22:00, 월요일 휴무", R.drawable.ato, null, "대전 유성구 농대로17번길 19"),
    Place("닭갈비", "마인네하우스", "042-254-3450", 36.36217, 127.34961, "매일 10:30 - 23:30", R.drawable.akdlsspgkdntm, null, "대전 유성구 대학로145번길 25"),
    Place("닭갈비", "맛존매콤닭불고기", "0507-1423-9926", 36.36243, 127.35074, "매일 11:00 - 22:00", R.drawable.aktwhsaozhaekfrqnfrhrl, null, "대전 유성구 대학로155번길 38"),
    Place("치킨", "닭섬", "0507-1364-0922", 36.36144, 127.35028, "매일 16:00 - 새벽 02:00", R.drawable.ekfrtja, null, "대전 유성구 대학로151번길 18"),
    Place("찜닭", "첫눈에찜한닭", "042-822-1126", 36.36288, 127.35028, "매일 11:00 - 22:00", R.drawable.cjtsnsdpwlagksekfr, null, "대전 유성구 궁동로18번길 53"),
    Place("곱창", "곱분이곱창", "042-822-6563", 36.36267, 127.35057, "매일 17:00 - 새벽 02:00", R.drawable.rhqqnsdlrhqckd, null, "대전 유성구 궁동로18번길 58"),
    Place("고기", "투더돼지", "0507-1416-8291", 36.36182, 127.35208, "매일 17:00 - 23:00", R.drawable.xnejehowl, null, "대전 유성구 농대로15번길 18"),
    Place("고기", "초원돌구이", "0507-1490-3395", 36.36194, 127.34961, "매일 11:00 - 22;30", R.drawable.chdnjsehfrndl, null, "대전 유성구 대학로145번길 21"),
    Place("고기", "팬텀팬피그", "042-822-9195", 36.36079, 127.34999, "매일 12:00 - 23:00", R.drawable.vosxjavosvlrm, null, "대전 유성구 대학로 149"),
    Place("고기", "아저씨", "042-825-3816" , 36.36194, 127.34983, "월-금 17:00 - 24:00, 토,일 12:00 -24:00", R.drawable.dkwjtl, null, "대전 유성구 대학로145번길 22"),
    Place("고기", "한마음정육식당", "042-867-2292", 36.36246, 127.35748, "화-일 11:00 - 22:00, 월요일 휴무", R.drawable.gksakdmawjddbrtlrekd, null, "대전 유성구 어은로 46"),
    Place("고기", "정통집", "0507-1486-9289", 36.36269, 127.34984, "매일 17:00 - 23:30", R.drawable.wjdxhdwlq, null, "대전 유성구 궁동로18번길 44"),
    Place("타코", "리코타코", "042-823-7234", 36.36217, 127.35143, "매일 11:00 - 22:00, 1,3,5번째 월요일 휴무", R.drawable.flzhxkzh, null, "대전 유성구 대학로163번길 37"),
    Place("파스타", "오늘은파스타", "042-826-0198", 36.36229, 127.34898, "월-금 11:00 - 21:00, 토,일요일 휴무", R.drawable.dhsmfdmsvktmxk, null, "대전 유성구 대학로137번길 28"),
    Place("파스타", "파스타부오노", "042-826-2579", 36.36256, 127.34984, "매일 11:00 - 21:30", R.drawable.vktmxkqndhsh, null, "대전 유성구 대학로145번길 36"),
    Place("파스타", "시멘트", "042-385-0808", 36.36128, 127.34828, "매일 11:00 - 20:30", R.drawable.tlapsxm, null, "대전 유성구 대학로 131"),
    Place("국밥", "육수당", "0507-1475-0320", 36.36267, 127.34779, "매일 10:00 - 23:00", R.drawable.dbrtnekd, null, "대전 유성구 궁동로18번길 8"),
    Place("찌개", "길선인", "042-823-8867", 36.36286, 127.35234, "월-금,일 10:30 - 20:30, 토 11:00 - 18:00", R.drawable.rlftjsdls, null, "대전 유성구 궁동로18번길 89"),
    Place("족발", "돌아온김삿갓", "042-825-2080", 36.36134, 127.35026, "영업시간 정보 없음", R.drawable.ehfdkdhsrlatktrkt, null ,"대전 유성구 대학로151번길 16"),
    Place("돈가스", "별리달리", "0507-1321-3161", 36.36286, 127.34984, "매일 11:00 - 21:30", R.drawable.qufflekffl, null, "대전 유성구 궁동로18번길 45"),
    Place("마라탕", "탕화쿵푸", "042-825-2008", 36.36320, 127.35001, "매일 11:00 - 23:00", R.drawable.xkdghkzndvn, null, "대전 유성구 대학로151번길 55"),
    Place("마라탕", "로충칭마라탕", "042-826-8088", 36.363, 127.35001, "매일 11:00 - 22:00", R.drawable.fhcndcldakfkxkd, null, "대전 유성구 대학로151번길 51"),
    Place("짜장면", "홍콩반점", "0507-1317-0410", 36.36153, 127.35004, "매일 11:00 - 21:00", R.drawable.ghdzhdqkswja, null, "대전 유성구 대학로151번길 19"),
    Place("카레", "아삼삼", "0507-1309-4185", 36.36217, 127.34961, "월-금 11:30 - 21:00, 토 11:30 - 20:00, 일요일 휴무", R.drawable.dktkatka, null, "대전 유성구 대학로145번길 25"),
    Place("스테이크", "코니스", "0507-1333-5776", 36.36313, 127.34844, "화-일 17:00 - 24:00", R.drawable.zhsltm, null, "대전 유성구 궁동로18번길 21-7"),
    Place("솥밥", "밥한톨", "0507-1384-7079", 36.35224, 127.37307, "매일 11:30 - 21:00", R.drawable.qkqgksxhf, null, "대전 서구 갈마역로25번길 17-11"),
    Place("돈가스", "바삭공장", "042-485-3777", 36.35742, 127.37124, "월-금 11:30 - 20:30, 토,일 11:30 - 20:00", R.drawable.qktkrrhdwkd, null, "대전 서구 계룡로367번길 109"),
    Place("텐동", "와타요업", "010-4792-3220", 36.35257, 127.37344, "매일 12;00 - 21:00", R.drawable.dhkxkdydjq, null, "대전 서구 갈마역로25번길 9-8"),
    Place("카이센동", "프레쉬맨", "0507-1356-8493", 36.35228,  127.37358, "화-금 17:00 - 새벽 01:00, 토,일 12:00 - 새벽 01:00, 월요일 휴무", R.drawable.vmfptnlaos, null, "대전 서구 갈마역로25번길 9-11"),
    Place("돈가스", "우츠", "0507-1373-1727", 36.35269, 127.37224, "수-일 11:40 - 21:00, 월,화요일 휴무", R.drawable.dncm, null, "대전 서구 갈마역로25번길 35"),
    Place("햄버거", "버기즈", "0507-1345-9517", 36.35108, 127.37283, "매일 11:30 - 20:50", R.drawable.qjrlwm, null, "대전 서구 계룡로 383-45"),
    Place("파스타", "갈마살롱", "010-7507-6476", 36.35116, 127.37244, "월,수-일 12:00 - 21:00, 화요일 휴무", R.drawable.rkfaktkffhd, null, "대전 서구 계룡로 399-1"),
    Place("뇨끼", "릴리쉬", "010-4799-9592", 36.35251, 127.37236, "월,수-일 12:00 - 21:00, 화요일 휴무", R.drawable.flffltnl, null, "대전 서구 갈마역로25번길 33-6"),
    Place("뇨끼", "소프트 포테이토 샵", "0507-1408-9935", 36.35396, 127.37016, "매일 12:00 - 21:30", R.drawable.thvmxmvhxpdlxhtiq, null, "대전 서구 신갈마로230번길 18"),
    Place("스테이크", "오렌지블루스", "070-4300-0401", 36.35261, 127.37373, "매일 12:00 - 21:30", R.drawable.dhfpswlqmffntm, null, "대전 서구 갈마역로25번길 9-3"),
    Place("파스타", "헤이스팅스", "010-4722-6558", 36.34458, 127.39091, "화-일 12:00 - 21:00, 월요일 휴무", R.drawable.gpdltmxldtm, null, "대전 서구 문정로112번길 52"),
    Place("후토마끼", "무이", "0507-1352-0327", 36.35111, 127.37251, "엽업시간 정보 없음", R.drawable.andl, null, "대전 서구 계룡로 399"),
    Place("후토마끼", "무야", "010-4922-2485", 36.35257, 127.37344, "화-일 17:30 - 새벽 01:00, 월요잃 휴무", R.drawable.andi, null, "대전 서구 갈마역로25번길 9-8"),
    Place("라자냐", "MOOD", "0507-1358-2790", 36.35294, 127.37028, "화-일 12:00 - 21:00, 월요일 휴무", R.drawable.mood, null, "대전 서구 계룡로407번길 73"),
    Place("떡볶이", "떡반집", "042-472-2921", 36.35231, 127.37489, "화-금 10:00 - 21:00, 토,일 10:00 - 20:00, 월요일 휴무", R.drawable.ejrqkswlq, null, "대전 서구 둔산로 8"),
    Place("두부두루치기", "진로집", "042-226-0914", 36.32586, 127.42474, "매일 11:30 - 22:00", R.drawable.wlsfhwlq, null, "대전 중구 중교로 45-5"),
    Place("짬뽕", "동은성", "042-252-3866", 36.33105, 127.42318, "화-금 17:00 - 20:30, 토,일 11:30 - 20:30, 월요일 휴무", R.drawable.ehddmstjd, null, "대전 중구 대종로 532-1"),
    Place("뇨끼", "카라멜", "0507-1338-0231", 36.32881, 127.42262, "매일 11:30 - 21:00", R.drawable.zkfkapf, null, "대전 중구 보문로288번길 19 아이리스빌딩"),
    Place("피자", "홀리데이세븐펍", "070-8834-0777", 36.33028, 127.42702, "화-목,일 17:30 - 23:00, 금,토 17:30 -24:00, 월요일 휴무", R.drawable.ghfflepdltpqmsvjq, null, "대전 중구 목척7길 11"),
    Place("연어", "연 이자카야 2호점", "042-365-0505", 36.32704, 127.42787, "화-일 11:00 - 21:30, 월요일 휴무", R.drawable.dusdlwkzkdi2ghwja, null, "대전 중구 중앙로156번길 48"),
    Place("빵", "성심당", "1588-8069", 36.32777, 127.42731, "매일 08:00 - 22:00", R.drawable.tjdtlaekd, null, "대전 중구 대종로480번길 15"),
    Place("돈가스", "소바공방", "042-863-3303", 36.39562, 127.40721, "매일 11:30 - 21:00", R.drawable.thqkrhdqkd, null, "대전 유성구 엑스포로446번길 36"),
    Place("족발", "광세족발", "042-863-6142", 36.39869, 127.40249, "월-토 17;00 -23:00, 일요일 휴무", R.drawable.rhkdtpwhrqkf, null, "대전 유성구 전민로22번길 51"),
    Place("고기", "마장동김씨", "0507-1351-1507", 36.3578, 127.34444, "월-금 16:00 - 24:00, 토,일 11:30 - 24:00", R.drawable.akwkdehdrlatl, null, "대전 유성구 대학로 48-23"),
    Place("오코노미야끼", "타마", "0507-1408-1128", 36.3575, 127.34444, "월-토 17:00 - 새벽 01:00, 일요일 휴무", R.drawable.xkak, null, "대전 유성구 온천북로 7"),
    Place("파스타", "인스", "0507-1353-2901", 36.35748, 127.34997, "매일 10:00 - 24:00", R.drawable.dlstm, null, "대전 유성구 온천동로65번길 54"),
    Place("국밥", "태평소국밥", "042-525-5820", 36.35748, 127.35021, "매일 00:00 - 24:00", R.drawable.xovudthrnrqkq, null, "대전 유성구 온천동로65번길 50"),
    Place("팟타이", "잇마이타이 봉명점", "0507-1420-5466", 36.35946, 127.34444, "매일 11:00 - 21:00", R.drawable.dltakdlxkdlqhdaudwja, null, "대전 유성구 문화원로 77 그랑펠리체 상가"),
    Place("라면", "스바라시라멘", "0507-1350-7921", 36.35946, 127.34444, "매일 11:00 - 21:00", R.drawable.tmqkfktlfkaps, null, "대전 유성구 문화원로 77 그랑펠리체 상가"),
    Place("고기", "맛찬들", "042-485-3766", 36.35867, 127.34469, "매일 11:00 - 23:00", R.drawable.aktcksemf, null, "대전 유성구 대학로 60"),
    Place("덮밥", "모쿠요비", "042-822-0972", 36.35867, 127.34467, "매일 11:30 - 20:30", R.drawable.ahzndyql, null, "대전 유성구 온천북로13번길 35"),
    Place("연어", "고치소사마", "070-4123-1990", 36.35727, 127.35149, "매일 11:00 - 22:00", R.drawable.rhclthtkak, null, "대전 유성구 온천동로65번길 26"),
    Place("라멘", "히토메보레", "042-825-7832", 36.35842, 127.34576, "화-일 11:30 - 21:00, 월요일 휴무", R.drawable.glxhapqhfp, null, "대전 유성구 온천북로33번길 35-17"),
    Place("파스타", "봉명가든", "042-384-7700", 36.35867, 127.34458, "매일 11:30 - 22:00", R.drawable.qhdaudrkems, null, "대전 유성구 대학로 60"),
    Place("파스타", "빠레뜨한남", "042-472-5585", 36.35269, 127.37987, "매일 11:30 - 21:00", R.drawable.qkfpemgksska, null, "대전 서구 대덕대로 226"),
    Place("규카츠", "규카츠정", "042-472-5795", 36.35451, 127.37827, "매일 11:00 - 21:00", R.drawable.rbzkcmwjd, null, "대전 서구 대덕대로241번길 20"),
    Place("카레", "미세노센세", "042-483-0366", 36.35427, 127.37691, "매일 11:30 - 21:30", R.drawable.altpshtpstp, null, "대전 서구 둔지로 52"),
    Place("돈가스", "설해돈", "0507-1385-1119", 36.35372, 127.37729, "매일 11:00 - 22:00", R.drawable.tjfgoehs, null, "대전 서구 대덕대로233번길 36"),
    Place("초밥", "초연정", "042-488-0971", 36.35327, 127.37827, "월,수-일 11:30 - 21:00, 화요일 휴무", R.drawable.chduswjd, null, "대전 서구 둔산로31번길 10-35"),
    Place("덮밥", "소복담다", "042-476-3033", 36.35006, 127.3786, "매일 11:30 - 20:30", R.drawable.thqhrekaek, null, "대전 서구 둔산남로9번길 45"),
    Place("피자", "리골레토 시카고피자", "042-471-2226", 36.35096, 127.38864, "매일 11:30 - 23:00", R.drawable.flrhffpxhtlzkrhvlwk, null, "대전 서구 둔산로 134"),
    Place("에그베네딕트", "모루", "042-486-2201", 36.34941, 127.37821, "화-일 10:30 - 15:30, 월요일 휴무", R.drawable.ahfn, null, "대전 서구 둔산남로9번길 29"),
    Place("파스타", "텀즈업브로", "042-489-1985", 36.35189, 127.38038, "매일 11:00 - 21:00", R.drawable.xjawmdjqqmfh, null, "대전 서구 대덕대로220번길 20"),
    Place("스테이크", "시그니처랩 갤러리아", "0507-1326-6086", 36.35178, 127.37811, "매일 10:30 - 20:30", R.drawable.tlrmslcjfoq, null, "대전 서구 대덕대로 211 타임월드 11층"),
    Place("햄버거", "쉐이크쉑", "042-720-6133", 36.35266, 127.37874, "매일 10:30 - 22:00", R.drawable.tnpdlzmtnpr, null, "대전 서구 대덕대로 219 EAST동 1층"),
    Place("라면", "멘야산다이메", "042-484-4129", 36.35229, 127.37727, "매일 11:30 - 22:00", R.drawable.apsditksekdlap, null, "대전 서구 둔산로31번길 13"),
    Place("돈가스", "어메이징카츠", "0507-1497-5583", 36.35071, 127.38169, "월-토 10:00 - 21:00, 일요일 휴무", R.drawable.djapdlwldzkcm, null, "대전 서구 둔산로74번길 12"),
    Place("낚지볶음", "시골길", "042-487-1638", 36.35451, 127.3773, "매일 11:00 - 21:30", R.drawable.tlrhfrlf, null, "대전 서구 둔산로31번길 61"),
    Place("초밥", "무젠스시", "042-488-1203", 36.35341, 127.37777, "매일 11:30 - 21:50", R.drawable.anwpstmtl, null, "대전 서구 둔산로31번길 38"),
    Place("연어", "연 이자카야", "0507-1335-3232", 36.35247, 127.37452, "매일 11:00 - 21:00", R.drawable.dusdlwkzkdi, null, "대전 서구 둔산로 4 신둔산빌딩"),
    Place("스테이크", "미도인", "042-472-9992", 36.35408, 127.37732, "매일 11:30 - 21:00", R.drawable.alehdls, null, "대전 서구 둔산로31번길 51"),
    Place("백반", "유성불백", "042-824-9862", 36.37489, 127.33792, "월-토 11:00 -21:00, 일요일 휴무", R.drawable.dbtjdqnfqor, null, "대전 유성구 죽동로242번길 8-12"),
    Place("스테이크", "토시살롱", "042-824-9972", 36.37201, 127.33985, "매일 11:00 - 22:00", R.drawable.xhtltkffhd, null, "대전 유성구 죽동로279번길 71-5"),
    Place("양갈비", "아자스", "0507-1357-5000", 36.37165, 127.3394, "매일 16:00 - 22:00", R.drawable.dkwktm, null, "대전 유성구 죽동로279번길 63-11")
)
var second = 0
var second2 = 0
lateinit var timer : Timer
lateinit var timer2 : Timer
lateinit var context3 : Context
lateinit var dice1 : ImageView
lateinit var dice2 : ImageView
class Fragment3: Fragment() {
    lateinit var lunch_name : TextView
    lateinit var dinner_name : TextView
    override fun onAttach(context: Context) {
        context3 = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        for (i in place_list){
            if (!(i.menu_name in lunch_menuList)){
                lunch_menuList.add(i.menu_name)
            }
        }
        setHasOptionsMenu(true)
        val activity =  inflater.inflate(R.layout.fragment3, container, false)
        dice1 = activity.findViewById<ImageView>(R.id.dice1)
        dice2 = activity.findViewById<ImageView>(R.id.dice2)


        val lunch = activity.findViewById<ImageView>(R.id.lunch_menu)
        val dinner = activity.findViewById<ImageView>(R.id.dinner_menu)

        lunch_name = activity.findViewById<TextView>(R.id.lunch_menu_name)
        dinner_name = activity.findViewById<TextView>(R.id.dinner_menu_name)

        lunch.setOnClickListener{
            val temp = lunch_name.text.toString()
            if (temp != ""){
                val intent = Intent(context, Specific_info3::class.java)
                intent.putExtra("menu_name", temp)
                startActivity(intent)
            }

        }
        dinner.setOnClickListener{
            val temp = dinner_name.text.toString()

            val intent = Intent(context, Specific_info3::class.java)
            intent.putExtra("menu_name", temp)
            startActivity(intent)
        }
        dice1.setOnClickListener {
                second = 0

                timer = Timer()
                Glide.with(this).load(R.raw.dice).into(dice1);
                val timerTask = object:TimerTask() {
                    override fun run() {
                        second += 5
                        if(second > 50) {
                            getActivity()?.runOnUiThread{
                                dice1.setImageResource(R.drawable.dice1)
                            }
                            timer.cancel()
                        } else {
                            getActivity()?.runOnUiThread{
                                var found = false
                                val temp1 = (0 until place_list.size).random()
                                val temp = place_list.get(temp1)
                                lunch_name.setText(temp.menu_name)
                                if (temp.pic != null){
                                    lunch.setImageResource(temp.pic)
                                    found = true
                                }
                                else if (temp.uri != null){
                                    lunch.setImageURI(temp.uri)
                                    found = true
                                }
//                                for (i in place_list){
//                                    if (i.menu_name == temp ){
//                                        if(i.pic != null){
//                                            lunch.setImageResource(i.pic)
//                                        }
//                                        else if (i.uri != null){
//                                            lunch.setImageURI(i.uri)
//                                        }
//                                        else{
//                                            lunch.setImageResource(R.drawable.restaurant)
//                                        }
//                                        found = true
//                                    }
//                                }
                                if (!found){
                                    lunch.setImageResource(R.drawable.restaurant)
                                }

                            }

                        }

                    }
                }

                timer.scheduleAtFixedRate(timerTask, 0, 500)


            }
        dice2.setOnClickListener {

            second2 = 0

            timer2 = Timer()
            Glide.with(this).load(R.raw.dice).into(dice2);
            val timerTask = object:TimerTask() {
                override fun run() {
                    second2 += 5
                    if(second2 > 50) {
                        getActivity()?.runOnUiThread{
                            dice2.setImageResource(R.drawable.dice1)
                        }
                        timer2.cancel()
                    } else {
                        getActivity()?.runOnUiThread{
                            var found = false
                            val temp1 = (0 until place_list.size).random()
                            val temp = place_list.get(temp1)
                            dinner_name.setText(temp.menu_name)
                            if (temp.pic != null){
                                dinner.setImageResource(temp.pic)
                                found = true
                            }
                            else if (temp.uri != null){
                                dinner.setImageURI(temp.uri)
                                found = true
                            }
//                            for (i in place_list){
//                                if (i.menu_name == temp ){
//                                    if(i.pic != null){
//                                        dinner.setImageResource(i.pic)
//                                    }
//                                    else if (i.uri != null){
//                                        dinner.setImageURI(i.uri)
//                                    }
//                                    else{
//                                        dinner.setImageResource(R.drawable.restaurant)
//                                    }
//                                    found = true
//                                }
//                            }
                            if (!found){
                                dinner.setImageResource(R.drawable.restaurant)
                            }

                        }

                    }

                }
            }

            timer2.scheduleAtFixedRate(timerTask, 0, 500)
        }
        return activity
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu3, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_lunch_menu -> {
                val intent = Intent(context3, Add_menu::class.java)
                intent.putExtra("time", "lunch")
                startActivity(intent)
                return true
            }
            R.id.add_dinner_menu -> {
                val intent = Intent(context3, Add_menu::class.java)
                intent.putExtra("time", "dinner")
                startActivity(intent)
                return true
            }
            else -> {
                return true
            }
        }
    }

}

class Place(val menu_name: String, val place_name: String, val num: String, val locN: Double, val locE: Double, val time: String, val pic: Int?, val uri: Uri?, val loc: String){

}
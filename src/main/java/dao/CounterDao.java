package dao;

import vo.Counter;

public class CounterDao {
	// 오늘 날짜의 카운트가 존재하는 지
	// 오늘 접속자수 확인
	public Counter selecctCounterByToday() {
		// SELECT * FROM counter
		// WHERE cnt_Date = CURDATE();
		return null;
	}
	
	// selecctCounterByToday() 결과가 없을 때
	public int insertCounter() {
		// INSERT INTO counter(cnt_date ,cnt_num) 
		// VALUES (CURDATE() , 1 )
		
		return 0;
	}
	
	
	// selecctCounterByToday() 결과가 있을 떄
	public int updateCounter() {
		// UPDATE counter SET cnt_num = cnt_num+1
		// WHERE cnt_date = CURDATE();
		
		return 0;
	}
	
	
	// 누적 접속자 수 
	public int selectCounterSum() {
		// SELECT SUM(cnt_num) FROM counter;
		
		
		return 0;
	}
}

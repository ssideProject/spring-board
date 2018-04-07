insert into member (name, id, age, passwd, reg, updt)
values('김김', 'id1111', '22', '1111', sysdate , sysdate);
insert into member (name, id, age, passwd, reg, updt)
values('김준성', 'id1212', '99', '1111', sysdate , sysdate);
insert into member (name, id, age, passwd, reg, updt)
values('이안 막케이', 'ian1111', '23', '1111', sysdate , sysdate);

ALTER TABLE member MODIFY (id varchar2(200) );

create table tbl_board(
	user_name varchar(30),
	viewcnt number(22),
	reg_date date default SYSDATE,
	writer varchar(50),
	content varchar(3000),
	title varchar(200),
	bno number(22),
	constraint tbl_board_PK primary key (bno)
)

create table tbl_member(
	user_id varchar(100),
	password varchar(100),
	email varchar(100),
	reg_date date default SYSDATE,
	up_date date default SYSDATE,
	constraint tbl_member_pk primary key (user_id)
	
)	


SELECT rownum,bno,title,content, b.regdate, viewcnt, writer
        FROM TBL_BOARD B, MEMBER M
        where B.writer = M.id;
        
select * from TBL_BOARD;
select * from MEMBER;


SELECT rownum, bno,title,content, b.regdate, viewcnt, userName
        FROM TBL_BOARD B, MEMBER M
        WHERE B.writer = M.id AND
        title like '%%';
        



------------------------------------------ 4/03

alter table TBL_board add(USERNAME varchar(50));

-- not null 처리를 해버리니 모두다 안들어간다.. 무슨문제인거지?
create table tbl_reply(
	bno integer,
	rno integer,
	replytext varchar(500),
	replyer varchar(100),
	userName varchar(100),
	secretReply varchar(1),
	regdate Date default SYSDATE,
	updatedate Date default SYSDATE,
	CONSTRAINT PK_reply PRIMARY KEY(rno)
)

create SEQUENCE reply_seq start with 1 increment by 1 maxvalue 1000;

select * from TBL_REPLY;

select rno, bno, replytext, replyer, userName, r.regdate, r.updatedate
		FROM tbl_reply r, member m;
		
select rno, bno, replytext, replyer, userName, r.regdate, r.updatedate
		FROM tbl_reply r, member m
		WHERE r.replyer = m.id AND bno=12
		ORDER BY rno;	

		
--------------------------------------------------------04/05

SELECT *
FROM(
	SELECT ROWNUM as rn, A.*
	FROM( SELECT r.rno, bno, r.replytext, r.replyer, secretReply, r.regdate, r.updatedate, m.Name, (SELECT writer FROM tbl_board WHERE bno=r.bno) AS writer
			FROM tbl_reply r, member m
			WHERE
			r.replyer=m.id AND bno= 37
			order by rno
		)A
	)WHERE rn BETWEEN 1 AND 5
-- 위의 SQL을 해석해보면 댓글과 맴버테이블에서 게시물 번호에 맞는 잡탕구리들을 가지고와서
-- 그 갯수를 ROWNUM이라고 말하는것같으며 잡탕구리들을 A라고 칭하고
-- 게 중에서 시작와 끝 번호에 맞는 레코드들을 불러오는것같다.
-- rownum은 오라클에서 지원하는 가상칼럼이였다
	
alter table tbl_reply add( secretReply varchar(1) default null);


---------------------------------------------------------04/06

delete from tbl_reply;
delete from tbl_reply where secretReply is null;

delete from tbl_reply where rno is not null;
alter table tbl_reply add constraint reply_FK foreign key (bno) references tbl_board(bno) on delete cascade;
--게시물을 삭제할때 같이 삭제해버리러면 on delete casecade 옵션을 사용하면 된다.
--그러나 그것을 방지하고싶다면.

----------------------------------------------------------
	
	
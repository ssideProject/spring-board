insert into member (name, id, age, passwd, reg, updt)
values('김김', 'id1111', '22', '1111', sysdate , sysdate);
insert into member (name, id, age, passwd, reg, updt)
values('김준성', 'id1212', '99', '1111', sysdate , sysdate);
insert into member (name, id, age, passwd, reg, updt)
values('이안 막케이', 'ian1111', '23', '1111', sysdate , sysdate);

ALTER TABLE member
MODIFY (id varchar2(200) );


SELECT bno,title,content, b.regdate, viewcnt, writer
        FROM TBL_BOARD B, MEMBER M
        where B.writer = M.id;
        
select * from TBL_BOARD;
select * from MEMBER;


SELECT bno,title,content, b.regdate, viewcnt, userName
        FROM TBL_BOARD B, MEMBER M
        WHERE B.writer = M.id AND
        title like '%%';
        



------------------------------------------ 4/03

alter table TBL_board add(USERNAME varchar(50));

// not null 처리를 해버리니 모두다 안들어간다.. 무슨문제인거지?
create table tbl_reply(
	bno integer,
	rno integer,
	replytext varchar(500),
	replyer varchar(100),
	userName varchar(100),
	regdate Date default SYSDATE,
	updatedate Date default SYSDATE,
	CONSTRAINT PK_reply PRIMARY KEY(rno)
)


drop table tbl_reply;

create SEQUENCE reply_seq start with 1 increment by 1 maxvalue 1000;

select * from TBL_REPLY;

select rno, bno, replytext, replyer, userName, r.regdate, r.updatedate
		FROM tbl_reply r, member m;
		
select rno, bno, replytext, replyer, userName, r.regdate, r.updatedate
		FROM tbl_reply r, member m
		WHERE r.replyer = m.id AND bno=12
		ORDER BY rno;	



        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
--        
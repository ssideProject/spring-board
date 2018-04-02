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
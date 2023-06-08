-- 시퀀스 : 자동으로 고유 숫자값을 생성해 주는 역할을 한다.
--         보통 기본키값에서 사용된다.
-- 참고 : MySQL이나 MariaDB 에서는 auto_increment가 존재한다.

-- 생성방법 
-- create seqence 이름(보통은 테이블이름_seq)
-- increment by 증가숫자(보통은1증가)
-- start with 시작숫자(보통은1)
-- nocycle ;
-- nocycle => 번호가 끝까지 간 후 다시 시작 안함 설정

-- 사용방법
-- insert 할때 시퀀스이름.nextval을 사용하면
-- 자동으로 삽입 할때 번호가 증가 된다.

select * from customer;
delete from customer;
commit;

CREATE SEQUENCE CUSTOMER_SEQ
INCREMENT BY 1
START WITH 1 
NOCYCLE;

INSERT INTO CUSTOMER(CUSTID,NAME,ADDRESS,PHONE)
VALUES(CUSTOMER_SEQ.NEXTVAL,'박지성','영국 맨체스터', '000-5000-0001');

INSERT INTO CUSTOMER(CUSTID,NAME,ADDRESS,PHONE)
VALUES(CUSTOMER_SEQ.NEXTVAL,'김연아','대한민국 서울', '000-6000-0001');

INSERT INTO CUSTOMER(CUSTID,NAME,ADDRESS,PHONE)
VALUES(CUSTOMER_SEQ.NEXTVAL,'장미란','대한민국 강원도', '000-7000-0001');

INSERT INTO CUSTOMER(CUSTID,NAME,ADDRESS,PHONE)
VALUES(CUSTOMER_SEQ.NEXTVAL,'추신수','미국 클리블랜드', '000-8000-0001');

INSERT INTO CUSTOMER(CUSTID,NAME,ADDRESS,PHONE)
VALUES(CUSTOMER_SEQ.NEXTVAL,'박세리','대한민국 대전', NULL);

COMMIT;
SELECT * FROM CUSTOMER ORDER BY CUSTID;

--------------------------------------------------------------------------------

-- 뷰 (VIEW) : 다른 테이블을 기반으로 만들어진 가상 테이블
--             데이터를 실제로 저장하지 않고 논리적으로만 존재하는 테이블이다.
--             일반 사용자는 뷰를 만들 수 없다.
--             권한을 받아야 한다.(SYSTEM 계정에서 GRANT CREATE VIEW TO 일반계정)
--             뷰는 삽입, 삭제, 수정이 목적이 아니라 검색(SELECT)가 목적이다.
--             여러 테이블을 하나로 합쳐서 하나의 뷰로 만들 수 도 있고
--             하나의 테이블을 여러개의 뷰를 만들 수도 있다.
--             같은 이름의 뷰가 존재하면 오류 발생

-- 만드는 방법:
--       같은 이름의 뷰가 존재하면 오류 발생
-- 방법1) CREATE VIEW 뷰이름
--       AS SELECT 문;

--       같은 이름의 뷰가 존재하면 기존의 있는 뷰를 삭제하고 새로 만든다.
-- 방법2) CREATE OR REPLACE VIEW 뷰이름
--       AS SELECT 문;

-- BANANA 고객이 주문한 제품의 이름을 검색해 보자.
SELECT P.제품명 ,O.주문일자
FROM 제품테이블 P, 주문테이블 O
WHERE P.제품번호 = O.주문제품 AND O.주문고객='banana';



-- 위 내용을 뷰로 만들어 보자
CREATE VIEW banana_select
as SELECT P.제품명 ,O.주문일자
FROM 제품테이블 P, 주문테이블 O
WHERE P.제품번호 = O.주문제품 AND O.주문고객='banana';

select * from banana_select;

-- 고객의 이름과 고객이  주문한 도서의 이름을 구하시오.
select c.name, b.bookname, b.price
from customer c, orders o ,BOOK b
where c.custid = o.custid and o.bookid = b.bookid
order by c.name;

commit;

create view v_BOOK
as select c.name, b.bookname, b.price
from customer c, orders o ,BOOK b
where c.custid = o.custid and o.bookid = b.bookid
order by c.name;

select * from v_book;

-- 이상미디어에서 출판한 도서를 구매한 고객의 이름을 보이시오.
select name from customer
where custid in(select custid from orders 
                where bookid in(select bookid from book where publisher = '이상미디어'));
                
create view 이상미디어_고객이름
as select name from customer
where custid in(select custid from orders 
                where bookid in(select bookid from book where publisher = '이상미디어'));
                
select * from 이상미디어_고객이름;                  
 -------------------------------------------------------------------------------
 
 -- 오라클 내장 함수 : 오라클 자체에서 가지고는 함수
 -- dual : 출력결과를 보기 위해 오라클에서 제공하는 가상 테이블
 -- 1. 숫자관련 내장 함수들
 --   1) 절대값 : ABS(숫자)
    select abs(-75),abs(+75),abs(75),abs(3.156),abs(-3.156) from dual;
 --   2) 올림 : ceil(숫자) , 소숫점 기준으로 올림
    select ceil(-75.41), ceil(75.41), ceil(75.61),ceil(-75.61) from dual;
 --   3) 버림 : floor(숫자),소숫점 기준으로 버림
    select floor(-75.41), floor(75.41),floor(75.61),floor(-75.61) from dual;     
 --   4) 버림 : trunc(숫자),trunc(숫자, 자릿수)
    select trunc(-75.41), trunc(75.41),trunc(75.61),trunc(-75.61) from dual;
    select trunc(-75.41,0), trunc(75.41,0),trunc(75.61,0),trunc(-75.61,0) from dual;
    select trunc(-75.41,1), trunc(75.41,1),trunc(75.61,1),trunc(-75.61,1) from dual;
    select trunc(-75.41,-1), trunc(75.41,-1),trunc(75.61,-1),trunc(-75.61,-1) from dual;--원단위절삭
  
 --   5) 반올림 : round(숫자), round(숫자,자릿수) : 자릿수 이하만 올림
    select round(-13.4441),round(13.4441),round(-13.5541),round(13.5541)from dual;
    select round(-13.4441,0),round(13.4441,0),round(-13.5541,0),round(13.5541,0)from dual;
    select round(-13.4441,1),round(13.4441,1),round(-13.5541,1),round(13.5541,1)from dual;
 --   6) power(숫자,숫자) : 제곱함수
    select power(2,3),power(3,2)from dual;
    -- 2 * 2 * 2,           3 * 3
 --   7) sqrt(숫자) : 제곱근
    select sqrt(4),sqrt(9),sqrt(10),   trunc(sqrt(10),1) from dual;
 --   8) sign(정수) : 음수인지,양수인지 판별 : 음수 -1, 양수1 
    select sing(-75.41),sing(75.41),sing(75.61),sing(-75.61) from dual;
 --   9) mod : 나눈 나머지 (java % 와 같음)
    select mod(27,2),mod(27,3),mod(27,4)from dual;
    
------------------------------------------------------------------------------------
-- 문자함수
-- 1. chr(아스키코드 =숫자) : 아스키코드에 해당하는 문자 표시
    select chr(97),chr(65),chr(49) from dual;
-- 2. concat(문자열, 문자열) : 두 문자열 결합
    select concat('ICT','인재개발월')from dual;
-- ** 3. lower(문자열) : 모든 글자를 소문자로 변경
-- ** 4. upper(문자열) : 모든 글자를 대문자로 변경
--    5. initcap(문자열) : 첫글자 대문자 나머지는 소문자로 만듬
    select lower('KOREA1231'),lower('KoRea'),lower('korea'),
            upper('KOREA1212'),upper('KoRea'),upper('korea'),
            initcap('KOREA1212'),initcap('KoRea'),initcap('korea')from dual;
   
    select * from 고객테이블 where upper(고객아이디) = 'BANANA';
    
--    6. trim(문자열) : 양쪽 공백 제거  
--    7. ltrim(문자열) : 왼쪽 공백 제거
--    8. rtrim(문자열) : 오른쪽 공백 제거
--    9. length(문자열) : 문자열의 길이
select trim('  oracle xe 21c  '),ltrim('  oracle xe 21c  '),rtrim('  oracle xe 21c  ') from dual;

select length('  oracle xe 21c  '),
    length(trim('  oracle xe 21c  ')),
    length(ltrim('  oracle xe 21c  ')),
    length(rtrim('  oracle xe 21c  ')) from dual;
--    10. lpad(문자열, 자리수, 왼쪽 공백을 메울 문자) : 자리수가 모자르면 잘린다.
--    11. rpad(문자열, 자리수, 오른쪽 공백을 메울 문자) : 자리수가 모자르면 잘린다.
select lpad('oracle xe',20,'*'),rpad('oracle xe',20,'*') from dual;
    select lpad('oracle xe',5,'*')a ,rpad('oracle xe',5,'*') b from dual;
 
--    12. substr(문자열,시작위치,갯수) ; 시작위치 1부터 , -는 오른쪽 출발
select substr('abcdefg', 2,2),
       substr('abcdefg', -2,2),
       substr('abcdefg', -3,5),
       substr('abcdefg', -5,3) from dual;

--    13. replace(문자열, 변경할 문자열, 변경 문자)
select replace('대한민국','민','♥민') from dual;

--    14. ASCII('아스키코드문자') : 숫자로 나타냄(chr()의 반대)
select ASCII('A'), ASCII('a'), ASCII('1') from dual;

--    15. instr(문자열, 찾는 문자열, 시작위치) : 문자열에서 문자열의 위치표시, index는 1부터
select instr('770704-1000000','-') from dual;  --  7
select instr('770704-1000000','0') from dual;  --  3(첫번째 0찾기)
select instr('770704-1000000','0',4) from dual;  --  5(두번째 0찾기)
select instr('770704-1000000','0',
    (select instr('770704-1000000','0') from dual)+1) from dual; -- 5(두번째 0찾기)
    
select instr('770704-1000000','0',1,1) from dual;  --3 (첫번째 0찾기)
select instr('770704-1000000','0',1,2) from dual;  --5 (첫번째 0찾기)
select instr('770704-1000000','0',1,3) from dual;  --9 (첫번째 0찾기)
    
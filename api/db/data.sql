truncate table company RESTART IDENTITY CASCADE ;
INSERT INTO public.company VALUES
                        (default, 'Google', 'Internet', default),
                        (default, 'Woolworth', 'Shopping', default),
                        (default, 'McDonald', 'Restaurant', default);



truncate table customer RESTART IDENTITY CASCADE ;
INSERT INTO customer VALUES
                     (default, 'mzl', '1234567', 'Meng', 'Zhaolong', default),
                     (default, 'xj', '131415', 'Xu', 'Jun', default);

truncate table companyuser RESTART IDENTITY CASCADE ;
INSERT INTO companyuser VALUES
                                (default, 'cyc', '88888',  'Cao', 'Yuchen', default, 1);

truncate table admin RESTART IDENTITY CASCADE ;
INSERT INTO admin VALUES
                    (default, 'ysw', '12123', 'Yu', 'Shuowen');



truncate table share RESTART IDENTITY CASCADE ;
INSERT INTO share VALUES
    (default,1,'A', 50.2),
    (default, 1, 'B', 28.82),
    (default, 1, 'C', 329.43);

truncate table companylisting RESTART IDENTITY CASCADE ;
INSERT INTO companylisting VALUES
    (default, 1, 3000),
    (default, 2, 2800),
    (default, 3, 0);

truncate table investlisting RESTART IDENTITY CASCADE ;
INSERT INTO investlisting VALUES
                               (default, 2, 200),
                               (default, 3, 3000);

truncate table portfolio RESTART IDENTITY CASCADE ;
INSERT INTO portfolio VALUES
                           (default, 1, 1, 2, 1),
                           (default, 2, 2, 3, 1);
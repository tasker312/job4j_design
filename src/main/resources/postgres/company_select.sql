select * from employees e left join departments d on e.dep_id = d.id;
select * from employees e right join departments d on e.dep_id = d.id;
select * from employees e full join departments d on e.dep_id = d.id;
select * from employees e cross join departments d;

select d.name from departments d left join employees e on e.dep_id = d.id where e.id is null;

select * from employees e left join departments d on e.dep_id = d.id;
select * from departments d right join employees e on e.dep_id = d.id;

select * from teens t1 cross join teens t2 where t1.gender = 'm' and t2.gender = 'f';

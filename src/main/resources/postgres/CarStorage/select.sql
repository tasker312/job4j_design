select 
	c.id, b.name as Кузов, e.name as Двигатель, t.name as "Коробка передач" 
from car c 
	join body b on c.body_id = b.id
	join engine e on c.engine_id = e.id
	join transmission t on c.trans_id = t.id;

select b.name from car c right join body b on c.body_id = b.id where c.body_id is null;
select e.name from car c right join engine e on c.engine_id = e.id where c.body_id is null;
select t.name from car c right join transmission t on c.trans_id = t.id where c.body_id is null;

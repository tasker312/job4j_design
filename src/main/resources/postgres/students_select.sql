select st.name as Имя, st.course as Курс, st.speciality as Специальность, un.name as Университет from students st join universities un on st.university_id = un.id;

select st.name as Имя, st.speciality as Специальность, un.name as Университет from students st join universities un on st.university_id = un.id where st.budget = true;

select st.speciality as Специальность, count(st.speciality) as Количество from students st join universities un on st.university_id = un.id group by st.speciality;
/*
* Migration script from schema with old data to the new schema.
*/
BEGIN TRAN

BEGIN TRY
   INSERT INTO new_data.full_name (id, first_name, last_name)
   (SELECT person_id, name, surname from old_data.person)
   INSERT INTO new_data.person (id, birth_date, email, name_id)
   (SELECT person_id, coalesce(CONVERT(datetime, SUBSTRING(ssn,0,CHARINDEX('-',ssn,0)), 112), CONVERT(datetime, '10000101', 112)), email, person_id
   FROM old_data.person
   )
   INSERT INTO new_data.account (id, username, password, person_id)
   (SELECT
       person_id,
       coalesce(username, ''),
       coalesce(password, CONVERT(varchar(255), NEWID())),
       person_id
   FROM old_data.person
   WHERE username IS NOT NULL
   )

    COMMIT TRAN

END TRY
BEGIN CATCH
    ROLLBACK TRAN
END CATCH

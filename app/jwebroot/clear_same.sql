DELETE
FROM
	`ey_document`
WHERE
	`remote_id` IN (
		SELECT
			a.remote_id
		FROM
			(
				SELECT
					`remote_id`
				FROM
					`ey_document`
				GROUP BY
					`remote_id`
				HAVING
					COUNT(`remote_id`) > 1
			) a
	)
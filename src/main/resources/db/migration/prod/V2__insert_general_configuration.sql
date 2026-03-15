INSERT INTO ims_plp_service_db.general_configuration
(id, code, value, status, created_at, updated_at)
VALUES
(nextval('ims_plp_service_db.general_configuration_seq'), 'MAX_RETRY', '3', 'ACTIVE', NOW(), NOW()),
(nextval('ims_plp_service_db.general_configuration_seq'), 'RETRY_AFTER', '5', 'ACTIVE', NOW(), NOW());
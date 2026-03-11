--------------------------------------------- lookup_category ---------------------------------------------------
CREATE SEQUENCE ims_plp_service_db.lookup_category_seq
START WITH 1
INCREMENT BY 1;

CREATE TABLE ims_plp_service_db.lookup_category (
    id BIGINT PRIMARY KEY DEFAULT nextval('ims_plp_service_db.lookup_category_seq'),
    code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
---------------------------------------------- lookup_value --------------------------------------------------

-- Sequence
CREATE SEQUENCE IF NOT EXISTS ims_plp_service_db.lookup_value_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Table
CREATE TABLE ims_plp_service_db.lookup_value (
    id BIGINT PRIMARY KEY DEFAULT nextval('ims_plp_service_db.lookup_value_seq'),
    value VARCHAR(255) NOT NULL,
    display_en VARCHAR(255) NOT NULL,
    display_ar VARCHAR(255),
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lookup_category_id BIGINT,

    CONSTRAINT fk_lookup_value_category
        FOREIGN KEY (lookup_category_id)
        REFERENCES ims_plp_service_db.lookup_category(id)
);

--------------------------------------------Request----------------------------------------------------

CREATE SEQUENCE ims_plp_service_db.request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE ims_plp_service_db.request (
    id BIGINT NOT NULL DEFAULT nextval('ims_plp_service_db.request_seq'),
    ims_product_id UUID NOT NULL UNIQUE,
    sync_batch_id VARCHAR(255),
    request_type VARCHAR(50) NOT NULL,
    request_payload JSONB NOT NULL,
    response_payload JSONB,
    parent_history_id VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    error_type VARCHAR(255),
    error_code VARCHAR(255),
    error_message TEXT,
    retry_count INTEGER NOT NULL DEFAULT 0,
    max_retries INTEGER NOT NULL DEFAULT 3,
    next_retry_at TIMESTAMP,
    started_at TIMESTAMP NOT NULL,
    completed_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_request PRIMARY KEY (id)
);


CREATE INDEX idx_request_status
ON ims_plp_service_db.request(status);

CREATE INDEX idx_request_sync_batch
ON ims_plp_service_db.request(sync_batch_id);

CREATE INDEX idx_request_next_retry
ON ims_plp_service_db.request(next_retry_at);
---------------------------------------------audit_change_log---------------------------------------------------
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE ims_plp_service_db.audit_change_log (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    entity_type VARCHAR(50) NOT NULL,
    entity_id UUID NOT NULL,
    action VARCHAR(50) NOT NULL,
    change_reason VARCHAR(255) NOT NULL,
    changed_fields JSONB,
    before_state JSONB NOT NULL,
    after_status JSONB NOT NULL,
    sync_batch_id UUID NOT NULL,
    request_id UUID,
    source_system VARCHAR(100) NOT NULL,
    triggered_by VARCHAR(100) NOT NULL,
    error_type VARCHAR(50),
    error_code VARCHAR(50),
    error_message VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_request FOREIGN KEY (request_id)
        REFERENCES ims_plp_service_db.request (id)
        ON DELETE SET NULL
);

CREATE INDEX idx_audit_entity_type
ON ims_plp_service_db.audit_change_log (entity_type);

CREATE INDEX idx_audit_entity_id
ON ims_plp_service_db.audit_change_log (entity_id);

CREATE INDEX idx_audit_sync_batch
ON ims_plp_service_db.audit_change_log (sync_batch_id);

-----------------------------------------------products-------------------------------------------------
CREATE TABLE ims_plp_service_db.products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id INTEGER NOT NULL UNIQUE,
    item_code VARCHAR(255) NOT NULL UNIQUE,
    name_en VARCHAR(255) NOT NULL,
    name_ar VARCHAR(255),
    description_en TEXT,
    description_ar TEXT,
    product_category VARCHAR(255) NOT NULL,
    product_sub_category VARCHAR(255),
    country_code VARCHAR(10),
    product_type VARCHAR(50),
    manufacturer VARCHAR(255) NOT NULL,
    attributes JSONB,
    sync_status VARCHAR(50) NOT NULL,
    product_version INTEGER NOT NULL DEFAULT 1,
    synced_version INTEGER NOT NULL DEFAULT 0,
    last_synced_at TIMESTAMP,
    last_sync_attempt_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    sales_channels JSONB NOT NULL,
    is_preorder BOOLEAN NOT NULL DEFAULT FALSE,
    is_serialized BOOLEAN NOT NULL DEFAULT FALSE,
    return_allowed BOOLEAN NOT NULL DEFAULT FALSE,
    free_shipping_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    inventory_check BOOLEAN NOT NULL DEFAULT TRUE,
    allow_non_stc BOOLEAN NOT NULL DEFAULT TRUE,
    publish_date TIMESTAMP NOT NULL,
    available_date TIMESTAMP NOT NULL,
    discontinued_date TIMESTAMP NOT NULL,
    preorder_date TIMESTAMP NOT NULL
);

CREATE INDEX idx_products_product_id ON ims_plp_service_db.products (product_id);
CREATE INDEX idx_products_item_code ON ims_plp_service_db.products (item_code);
CREATE INDEX idx_products_category ON ims_plp_service_db.products (product_category);
CREATE INDEX idx_products_sync_status ON ims_plp_service_db.products (sync_status);

-----------------------------------------------sync_schedules-------------------------------------------------
CREATE TABLE ims_plp_service_db.sync_schedules (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    sync_batch_id VARCHAR(255) NOT NULL,
    scheduled_at TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    executed_at TIMESTAMP,
    completed_at TIMESTAMP,
    total_count INTEGER NOT NULL DEFAULT 0,
    success_count INTEGER NOT NULL DEFAULT 0,
    failed_count INTEGER NOT NULL DEFAULT 0,
    created_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_sync_schedules_batch
ON ims_plp_service_db.sync_schedules (sync_batch_id);

CREATE INDEX idx_sync_schedules_status
ON ims_plp_service_db.sync_schedules (status);

CREATE INDEX idx_sync_schedules_scheduled_at
ON ims_plp_service_db.sync_schedules (scheduled_at);

-----------------------------------------------general_configuration-------------------------------------------------
-- Sequence
CREATE SEQUENCE IF NOT EXISTS ims_plp_service_db.general_configuration_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Table
CREATE TABLE ims_plp_service_db.general_configuration (
    id BIGINT PRIMARY KEY
        DEFAULT nextval('ims_plp_service_db.general_configuration_seq'),
    code VARCHAR(255) NOT NULL UNIQUE,
    value VARCHAR(255) NOT NULL,
    status VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_general_configuration_status
ON ims_plp_service_db.general_configuration(status);
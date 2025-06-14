INSERT INTO PROJECT (compartment_id,
tenant_id,
 display_name,
 short_name,
 description,
 lifecycle_state,
 config_status,
 stores, groups,
 protected,
 vetted,
 version)
 VALUES (1,
 11,
 'Display 1',
 'D1',
 'Description for compartment 1',
 'ACTIVE',
 'CONFIGURED',
 'id: 123, type : RAW, bucket: bucket1, autoTiering: Disabled, isObjectEventsEnabled: true',
 'ocid: ocid1, name: group name1',
 TRUE,
 TRUE,
 125);

 INSERT INTO PROJECT (compartment_id,
 tenant_id,
  display_name,
  short_name,
  description,
  lifecycle_state,
  config_status,
  stores, groups,
  protected,
  vetted,
  version)
  VALUES (2,
  22,
  'Display 2',
  'D2',
  'Description for compartment 2',
  'FAILED',
  'MISSED',
  'id: 1234567, type : RAW, bucket: bucket2, autoTiering: Disabled, isObjectEventsEnabled: true',
  'ocid: ocid2, name: group name2',
  FALSE,
  FAlSE,
  125);
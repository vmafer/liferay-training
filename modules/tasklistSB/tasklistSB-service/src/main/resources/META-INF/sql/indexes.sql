create index IX_FEE50C63 on Tasklist_Task (groupId);
create index IX_F9C78A5B on Tasklist_Task (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_14D4EB9D on Tasklist_Task (uuid_[$COLUMN_LENGTH:75$], groupId);
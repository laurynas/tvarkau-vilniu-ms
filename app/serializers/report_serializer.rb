class ReportSerializer < ActiveModel::Serializer
  attributes :id,
             :ref_no,
             :report_type_id,
             :lat,
             :lng,
             :user_id,
             :report_status_id,
             :description,
             :answer,
             :license_plate_no,
             :violation_timestamp,
             :completed_at

  has_many :report_photos
end

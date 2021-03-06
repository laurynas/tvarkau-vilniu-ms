class Report < ApplicationRecord
  belongs_to :city
  belongs_to :user
  belongs_to :report_type
  belongs_to :report_status

  has_many :report_photos

  validates :description,
            :address,
            :report_type,
            :report_status,
            presence: true

  validates :lat,
            presence: true,
            numericality: {
              greater_than_or_equal_to: -90,
              less_than_or_equal_to: 90
            }

  validates :lng,
            presence: true,
            numericality: {
              greater_than_or_equal_to: -180,
              less_than_or_equal_to: 180
            }

  validates :license_plate_no, presence: true, if: -> { report_type&.requires_license_plate_no? }
  validates :violation_timestamp, presence: true, if: -> { report_type&.requires_violation_timestamp? }
end

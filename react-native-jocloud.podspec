require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
    s.name           = "react-native-jocloud"
    s.version        = "1.1.0"
    s.summary        = "react-native-jocloud"
    s.homepage       = "https://jocloud.com"
    s.license        = { :file => './LICENSE' }
    s.authors        = { 'Your Company' => 'email@example.com' }
    s.platform       = :ios, "9.0"
    s.static_framework = true

    s.source         = { :git => package["repository"]["url"] }
    s.source_files   = 'ios/RCTAgora/**/*.{h,m,swift}'
    s.source           = { :path => '.' }
    s.source_files = 'ios/**/*.{h,m}'

    s.dependency 'React'
    s.dependency "thunder/thunderboltdynamic", "3.0.27"
end

param sku object
param appServicePlanName string 
param location string
param webAppName string
param linuxFxVersion string = 'JAVA|11-java11'



resource appServicePlan 'Microsoft.Web/serverfarms@2022-03-01' = {
  name: '${appServicePlanName}-${uniqueString(resourceGroup().id)}'
  location: location
  properties: {
    reserved: true
  }
  sku: sku
  kind: 'linux'
}

resource appService 'Microsoft.Web/sites@2020-06-01' = {
  name: webAppName
  location: location
  properties: {
    serverFarmId: appServicePlan.id
    siteConfig: {
      linuxFxVersion: linuxFxVersion
    }
  }
}

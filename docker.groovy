parameters {
    string(
            description: '输入版本号',
            name: 'version',
            defaultValue: '4.0'
    )
}

def version = "${params.version}"

node {
    stage('git chekout') {
        git branch: 'master', url: 'https://gitee.com/fastjrun/ddns.git'
    }
    docker.image('maven:3.8.6-openjdk-8').inside(""){
        stage('package') {
            sh 'sh build.sh package_server'
        }
    }
    stage('docker prepare') {
        dir('output') {
            stash 'tarOutput'
        }
    }
    stage('docker build && push') {
        parallel(
                'docker build && push amd64': {
                    node('amd64') {
                        sh 'rm -rf output'
                        dir('output') {
                            unstash 'tarOutput'
                        }
                        sh 'cd output && docker build . -t pi4k8s/ddns:$version-amd64'
                        sh 'docker push pi4k8s/ddns:$version-amd64'
                    }
                },
                'docker build && push arm64': {
                    node('arm64') {
                        sh 'rm -rf output'
                        dir('output') {
                            unstash 'tarOutput'
                        }
                        sh 'cd output && docker build . -t pi4k8s/ddns:$version-arm64'
                        sh 'docker push pi4k8s/ddns:$version-arm64'
                    }

                }
        )

    }
    stage('manifest'){
        try {
            sh 'docker manifest rm pi4k8s/ddns:$version'
        }catch(ignored){
            echo "some thing wrong"
        }
        sh 'docker manifest create pi4k8s/ddns:$version pi4k8s/ddns:$version-amd64 pi4k8s/ddns:$version-arm64'
        sh 'docker manifest annotate pi4k8s/ddns:$version pi4k8s/ddns:$version-amd64 --os linux --arch amd64'
        sh 'docker manifest annotate pi4k8s/ddns:$version pi4k8s/ddns:$version-arm64 --os linux --arch arm64'
        sh 'docker manifest push pi4k8s/ddns:$version'
    }
}

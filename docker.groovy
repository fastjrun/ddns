node {
    stage('git chekout') {
        git branch: 'master', url: 'https://gitee.com/fastjrun/ddns.git'
    }
    stage('package ddns') {
        sh 'sh build.sh package_server'
    }
    stage('docker prepare') {
        dir('output') {
            stash 'tarOutput'
        }
    }
    stage('docker build && push') {
        parallel(
                'docker build && push amd64': {
                    sh 'rm -rf output'
                    dir('output') {
                        unstash 'tarOutput'
                    }
                    sh 'cd output && docker build . -t pi4k8s/ddns:4.0-amd64'
                    sh 'docker push pi4k8s/ddns:4.0-amd64'
                },
                'docker build && push arm64': {
                    node('arm64') {
                        sh 'rm -rf output'
                        dir('output') {
                            unstash 'tarOutput'
                        }
                        sh 'cd output && docker build . -t pi4k8s/ddns:4.0-arm64'
                        sh 'docker push pi4k8s/ddns:4.0-arm64'
                    }

                }
        )

    }
    stage('manifest'){
        try {
            sh 'docker manifest rm pi4k8s/ddns:4.0'
        }catch(exc){
            echo "some thing wrong"
        }
        sh 'docker manifest create pi4k8s/ddns:4.0 pi4k8s/ddns:4.0-amd64 pi4k8s/ddns:4.0-arm64'
        sh 'docker manifest annotate pi4k8s/ddns:4.0 pi4k8s/ddns:4.0-amd64 --os linux --arch amd64'
        sh 'docker manifest annotate pi4k8s/ddns:4.0 pi4k8s/ddns:4.0-arm64 --os linux --arch arm64'
        sh 'docker manifest push pi4k8s/ddns:4.0'
    }
}

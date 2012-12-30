module.exports = function(grunt) {
    grunt.loadNpmTasks('grunt-shell');
    grunt.loadNpmTasks('grunt-contrib-watch');


    grunt.initConfig({

        shell: {
            mvncompile: {
                command: 'mvn compile',
                stdout: true
            }
        },

        watch: {
            files: '**/*.java',
            tasks: ['shell']
        }

    });

    grunt.registerTask('default', 'watch');
}

module.exports = {
    presets: [
            ['@vue/cli-plugin-babel/preset', {
            useBuiltIns: 'entry',
            targets: {
                ie: '11'
            }
        }]
    ],
    plugins: [
        '@babel/plugin-proposal-class-properties',
        '@babel/plugin-transform-classes'
    ]
}
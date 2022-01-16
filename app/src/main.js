import { createApp, h } from 'vue'
import HomeComponent from './components/Home'
import BoardListComponent from './components/BoardList'
import BoardFormComponent from './components/BoardForm'
import ErrorComponent from './components/Error'

//const { createApp, h } = Vue

const routes = {
    '/': HomeComponent,
    '/faq': BoardListComponent,
    '/faq/form': BoardFormComponent,
    '/error': ErrorComponent
}

const SimpleRouter = {
    data: () => ({
        currentRoute: window.location.pathname
    }),

    computed: {
        CurrentComponent() {
            return routes[this.currentRoute] || ErrorComponent
        }
    },

    render() {
        return h(this.CurrentComponent)
    }
}

createApp(SimpleRouter).mount('#app')
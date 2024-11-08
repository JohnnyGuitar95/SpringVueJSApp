<template>
  <main>
    <Navbar/>
    <div class="my-5">
      <div class="mx-auto w-50" style="max-width: 100%;">
        <h2 class="text-center mb-3">Обновить данные об отдела</h2>
        <form @submit.prevent="updateDepartment">
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="name" class="form-label">Название отдела</label>
              <input id="name" type="text" name="name" class="form-control" placeholder="Название отдела" required
                     v-model="department.name">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="note" class="form-label">Примечание</label>
              <input id="note" type="text" name="note" class="form-control" placeholder="Примечание" required
                     v-model="department.note">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="parentDepartment" class="form-label">Родительский отдел</label>
              <select id="parentDepartment" class="form-control" v-model="department.parentDepartmentId">
                <option value="">Нет</option>
                <option v-for="dept in departments" :key="dept.id" :value="dept.id">{{ dept.name }}</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Обновить">
            </div>
          </div>
          <div>
            <p v-if="message" class="text-success">{{ message }}</p>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'UpdateDepartment',
  components: {
    Navbar
  },

  data() {
    return {
      department: {
        id: '',
        name: '',
        note: '',
        parentDepartmentId: null
      },
      departments: [],
      message: ''
    };
  },

  beforeMount() {
    this.getDepartments();
  },

  methods: {
    getDepartments() {
      fetch('http://localhost:7000/departmentsApi/departments')
          .then(res => res.json())
          .then(data => {
            this.departments = data;
            this.getDepartment();
          })
          .catch(error => console.error('Error fetching departments:', error));
    },

    getDepartment() {
      fetch(`http://localhost:7000/departmentsApi/department/${this.$route.params.id}`)
          .then(res => res.json())
          .then(data => {
            this.department = data;
            console.log(this.department);
          })
          .catch(error => console.error('Error fetching department:', error));
    },

    updateDepartment() {
      fetch(`http://localhost:7000/departmentsApi/department`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.department)
      })
          .then(response => {
            if (response.ok) {
              this.message = 'Отдел успешно обновлен!';
              this.$router.push('/departmentsHome');
            } else {
              throw new Error('Ошибка при обновлении отдела');
            }
          })
          .catch(error => {
            console.error('Error updating department:', error);
            this.message = 'Произошла ошибка при обновлении отдела';
          });
    }
  }
}
</script>